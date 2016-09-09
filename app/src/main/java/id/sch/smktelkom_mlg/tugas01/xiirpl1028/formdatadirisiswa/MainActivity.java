package id.sch.smktelkom_mlg.tugas01.xiirpl1028.formdatadirisiswa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    EditText etNama, etTahun, etNoHP;
    Button bOK;
    TextView tvHasil, tvHobi;
    String hasil = "";
    RadioGroup rgGender;
    CheckBox cbMN, cbBS, cbBR, cbBG, cbMF;
    int nHobi;
    Spinner spProvinsi, spKota;
    String[][] arKota = {{"Jakarta Barat", "Jakata Pusat", "Jakarta Selatan", "Jakarta Timur", "Jakarta Utara"},
            {"Bandung", "Cirebon", "Bekasi"}, {"Semarang", "Magelang", "Surakarta"}, {"Surabaya", "Malang", "Blitar"}, {"Denpasar"}};
    ArrayList<String> listKota = new ArrayList();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.editTextNama);
        etTahun = (EditText) findViewById(R.id.editTextTahun);
        etNoHP = (EditText) findViewById(R.id.editTextNoHP);
        bOK = (Button) findViewById(R.id.buttonOK);
        rgGender = (RadioGroup) findViewById(R.id.RadioGroupGender);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);
        cbMN = (CheckBox) findViewById(R.id.checkBoxMN);
        cbBS = (CheckBox) findViewById(R.id.checkBoxBS);
        cbBR = (CheckBox) findViewById(R.id.checkBoxBR);
        cbBG = (CheckBox) findViewById(R.id.checkBoxBG);
        cbMF = (CheckBox) findViewById(R.id.checkBoxMF);
        tvHobi = (TextView) findViewById(R.id.textViewHobi);
        spProvinsi = (Spinner) findViewById(R.id.spinnerProvinsi);
        spKota = (Spinner) findViewById(R.id.spinnerKota);

        cbMN.setOnCheckedChangeListener(this);
        cbBS.setOnCheckedChangeListener(this);
        cbBR.setOnCheckedChangeListener(this);
        cbBG.setOnCheckedChangeListener(this);
        cbMF.setOnCheckedChangeListener(this);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listKota);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKota.setAdapter(adapter);

        spProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                listKota.clear();
                listKota.addAll(Arrays.asList(arKota[pos]));
                //adapter.setProvinsi((String)spProvinsi.getItemAtPosition(pos));
                adapter.notifyDataSetChanged();
                spKota.setSelection(0);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NameYearHP();
                Gender();
                Hobi();
                Asal();
                write();

            }
        });

    }

    private void Asal() {
        hasil += "Asal      : " + spKota.getSelectedItem().toString() + ", " + spProvinsi.getSelectedItem().toString();
    }

    private void Hobi() {
        String hobi = "Hobi      :\n";
        int startlen = hobi.length();
        if (cbMN.isChecked()) hobi += "                " + cbMN.getText() + "\n";
        if (cbBS.isChecked()) hobi += "                " + cbBS.getText() + "\n";
        if (cbBR.isChecked()) hobi += "                " + cbBR.getText() + "\n";
        if (cbBG.isChecked()) hobi += "                " + cbBG.getText() + "\n";
        if (cbMF.isChecked()) hobi += "                " + cbMF.getText() + "\n";

        if (hobi.length() == startlen) hobi = "Tidak ada pada pilihan hobi";

        hasil += hobi + "\n";
    }

    private void write() {
        tvHasil.setText(hasil);
        hasil = "";
    }

    private void Gender() {
        String gender = null;

        if (rgGender.getCheckedRadioButtonId() != -1) {
            RadioButton rb = (RadioButton)
                    findViewById(rgGender.getCheckedRadioButtonId());
            gender = rb.getText().toString();
        }
        if (gender == null) {
            hasil += "Belum memilih gender";
        } else {
            hasil += "Gender  : " + gender;
        }
        hasil += "\n";
    }

    private void NameYearHP() {
        if (isValid()) {
            String nama = etNama.getText().toString();
            String hp = etNoHP.getText().toString();
            int tahun = Integer.parseInt(etTahun.getText().toString());
            int usia = 2016 - tahun;
            hasil += "Nama    : " + nama + "\nUmur     :  " + usia + " tahun\nNo HP   : " + hp;
        } else if (!isValid()) {
            hasil += "Nama atau Tahun Kelahiran atau Nomor HP belum benar";
        }
        hasil += "\n";
    }

    private boolean isValid() {
        boolean valid = true;

        String nama = etNama.getText().toString();
        String tahun = etTahun.getText().toString();
        String hp = etNoHP.getText().toString();

        if (nama.isEmpty()) {
            etNama.setError("Nama belum diisi");
            valid = false;
        } else if (nama.length() < 3) {
            etNama.setError("Nama minimal 3 karakter");
            valid = false;
        } else {
            etNama.setError(null);
        }

        if (tahun.isEmpty()) {
            etTahun.setError("Tahun kelahiran belum diisi");
            valid = false;
        } else if (tahun.length() != 4) {
            etTahun.setError("Format tahun kelahiran bukan yyyy");
            valid = false;
        } else {
            etTahun.setError(null);
        }

        if (hp.isEmpty()) {
            etNoHP.setError("Nomor HP belum diisi");
            valid = false;
        } else if (hp.length() != 12) {
            etNoHP.setError("Nomor HP harus 12 digit");
            valid = false;
        } else {
            etNoHP.setError(null);
        }

        return valid;

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) nHobi += 1;
        else nHobi -= 1;

        tvHobi.setText("Hobi (" + nHobi + " terpilih)");
    }
}
