package itesm.mx.a01196362_examen_ahorroenergia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class FragAgregarEvento extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private String[] arraySpinner;
    private TextView tvNombre;
    private TextView tvWatts;
    private ImageView ivElectrodomestico;
    private TextView tvHora;
    private EditText etConsumo;
    private Button btnAgregar, btnAgregarImagen;
    private String nombre;
    private String fecha;
    private double consumo_electrodomestico;
    private ArrayList<Electrodomestico> arrayListElectrodomesticos;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    View view;

    EventoOperators operacionesEvento;
    public FragAgregarEvento() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_agregar_evento, container, false);
        operacionesEvento = new EventoOperators(getContext());
        operacionesEvento.open();


        InfoAparatos electrodomesticos = new InfoAparatos();
        arrayListElectrodomesticos = electrodomesticos.getListaElectrodomesticos();


        List<String> arraySpinner = new ArrayList<>();
        for(int i = 0; i < arrayListElectrodomesticos.size() ; i++){
            arraySpinner.add(arrayListElectrodomesticos.get(i).getNombre());
        }


        tvNombre = view.findViewById(R.id.text_nombre);
        tvWatts = view.findViewById(R.id.text_watts);
        tvHora = view.findViewById(R.id.text_hora);
        ivElectrodomestico = view.findViewById(R.id.image_electrodomestico);
        etConsumo = view.findViewById(R.id.edit_consumo);
        btnAgregar = view.findViewById(R.id.btn_agregar);
        btnAgregarImagen = view.findViewById(R.id.btn_agregar_imagen);
        btnAgregarImagen.setOnClickListener(this);
        btnAgregar.setOnClickListener(this);

        fecha = getDateTime();
        tvHora.setText(fecha);

        Spinner spinner_electrodomesticos = (Spinner) view.findViewById(R.id.spinner_electrodomesticos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        spinner_electrodomesticos.setAdapter(adapter);
        spinner_electrodomesticos.setOnItemSelectedListener(this);
        return view;
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(); return dateFormat.format(date);
    }

    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
        tvWatts.setText("Consumo: " + String.valueOf(arrayListElectrodomesticos.get(position).getWatts()) + "Watts/hr");
        ivElectrodomestico.setImageResource(arrayListElectrodomesticos.get(position).getIdImagen());
        nombre = arrayListElectrodomesticos.get(position).getNombre();
        consumo_electrodomestico = arrayListElectrodomesticos.get(position).getWatts();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivElectrodomestico.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parentView) {
    }


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn_agregar_imagen){
            dispatchTakePictureIntent();
        }
        else {

            if (etConsumo.getText().toString().equals("")) {
                Toast.makeText(getContext(), "Por favor ingresa la cantidad de horas que usaste.", Toast.LENGTH_SHORT).show();
            } else {
                String consumo = etConsumo.getText().toString();
                Bitmap bitmap = ((BitmapDrawable) ivElectrodomestico.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] byteArray = baos.toByteArray();

                Evento evento = new Evento(String.valueOf(Double.parseDouble(consumo) * consumo_electrodomestico), nombre, fecha, byteArray);
                long id = operacionesEvento.addEvent(evento);
                evento.setId(id);

                Toast.makeText(getContext(), "Evento añadido", Toast.LENGTH_SHORT).show();

                FragmentManager fm = getFragmentManager();
                FragEvento f = (FragEvento) fm.findFragmentByTag("TAG_EVENTOS");
                f = new FragEvento();
                fm.beginTransaction().replace(R.id.fragment_container, f, "TAG_EVENTOS").commit();
            }
        }

    }
}

