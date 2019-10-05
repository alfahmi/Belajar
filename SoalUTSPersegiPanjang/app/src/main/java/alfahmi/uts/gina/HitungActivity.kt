package alfahmi.uts.gina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.hitung_main.*

class HitungActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hitung_main)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        btn_jumlah.setOnClickListener{
            val Panjang = toDouble(edt_panjang.text.toString().trim())
            val Lebar = toDouble(edt_lebar.text.toString().trim())
            var theError = false

            if (Panjang == null)  {
                theError = true
                edt_panjang.error = "Maaf Field ini tidak boleh Kosong"
            }
            if ( Lebar == null) {
                theError = true
                edt_lebar.error = "Maaf Field ini tidak boleh Kosong"
            }
            if (!theError) {
                val luas = Panjang as Double * Lebar as Double
                val keliling = (2 * Panjang) + (2 * Lebar)
                val diagonal = Math.sqrt(Math.pow(Panjang, 2.0) + Math.pow(Lebar, 2.0))

                tv_keliling.text = keliling.toString()
                tv_luas.text = luas.toString()
                tv_diagonal.text = diagonal.toString()
            }
        }

        btn_reset.setOnClickListener {
            tv_keliling.text = 0.toString()
            tv_luas.text = 0.toString()
            tv_diagonal.text = 0.toString()
            edt_panjang.text.clear()
            edt_lebar.text.clear()
        }
    }

    private fun toDouble(str: String): Double? {
        return try {
            str.toDouble()
        }catch (e: NumberFormatException) {
            null
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
