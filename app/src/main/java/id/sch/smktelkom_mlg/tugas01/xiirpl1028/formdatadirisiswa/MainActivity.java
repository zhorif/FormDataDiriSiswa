package id.sch.smktelkom_mlg.tugas01.xiirpl1028.formdatadirisiswa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    EditText etNama;
    EditText etTahun;
    Button bOK;
    TextView tvHasil, tvHobi;
    String hasil = "";
    RadioGroup rgGender;
    CheckBox cbMN, cbBS, cbBR, cbBG, cbMF;
    int nHobi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.editTextNama);
        etTahun = (EditText) findViewById(R.id.editTextTahun);
        bOK = (Button) findViewById(R.id.buttonOK);
        rgGender = (RadioGroup) findViewById(R.id.RadioGroupGender);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);
        cbMN = (CheckBox) findViewById(R.id.checkBoxMN);
        cbBS = (CheckBox) findViewById(R.id.checkBoxBS);
        cbBR = (CheckBox) findViewById(R.id.checkBoxBR);
        cbBG = (CheckBox) findViewById(R.id.checkBoxBG);
        cbMF = (CheckBox) findViewById(R.id.checkBoxMF);
        tvHobi = (TextView) findViewById(R.id.textViewHobi);

        cbMN.setOnCheckedChangeListener(this);
        cbBS.setOnCheckedChangeListener(this);
        cbBR.setOnCheckedChangeListener(this);
        cbBG.setOnCheckedChangeListener(this);
        cbMF.setOnCheckedChangeListener(this);

        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NameYear();
                Gender();
                Hobi();

                write();

            }
        });

    }

    private void Hobi() {
        String hobi = "Hobi       :\n";
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

    private void NameYear() {
        if (isValid()) {
            String nama = etNama.getText().toString();
            int tahun = Integer.parseInt(etTahun.getText().toString());
            int usia = 2016 - tahun;
            hasil += "Nama    : " + nama + "\nUmur     :  " + usia + " tahun";
        } else if (!isValid()) {
            hasil += "Nama atau Tahun Kelahiran belum benar";
        }
        hasil += "\n";
    }

    private boolean isValid() {
        boolean valid = true;

        String nama = etNama.getText().toString();
        String tahun = etTahun.getText().toString();

        if (nama.isEmpty()) {
            etNama.setError("Nama Belum diisi");
            valid = false;
        } else if (nama.length() < 3) {
            etNama.setError("Nama minimal 3 karakter");
            valid = false;
        } else {
            etNama.setError(null);
        }

        if (tahun.isEmpty()) {
            etTahun.setError("Tahun Kelahiran belum diisi");
            valid = false;
        } else if (tahun.length() != 4) {
            etTahun.setError("Format Tahun Kelahiran bukan yyyy");
            valid = false;
        } else {
            etTahun.setError(null);
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
