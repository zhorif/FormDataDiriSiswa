package id.sch.smktelkom_mlg.tugas01.xiirpl1028.formdatadirisiswa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText etNama;
    EditText etTahun;
    Button bOK;
    TextView tvHasil;
    String hasil = "";
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.editTextNama);
        etTahun = (EditText) findViewById(R.id.editTextTahun);
        bOK = (Button) findViewById(R.id.buttonOK);
        rgGender = (RadioGroup) findViewById(R.id.RadioGroupGender);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);

        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NameYear();
                Gender();

                write();

            }
        });

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
            hasil = "Data Belum Lengkap";
        } else {
            hasil += "Gender  : " + gender + "\n";
        }
    }

    private void NameYear() {
        if (isValid()) {
            String nama = etNama.getText().toString();
            int tahun = Integer.parseInt(etTahun.getText().toString());
            int usia = 2016 - tahun;
            hasil += "Nama    : " + nama + "\nUmur     :  " + usia + " tahun\n";

        }
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

}
