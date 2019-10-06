## SoalUTSPersegiPanjang
Adalah aplikasi berbasis program kotlin yang mengacu pada aplikasi [SoalUTS](https://github.com/andreseptian07/Contoh-Pembelajaran/tree/master/SoalUTS) milik Pak [andreseptian07](https://github.com/andreseptian07), hanya saja aplikasi tersebut di buat ulang dengan codingan sesederhana mungkin.
### Codingan
Berikut codingan Kotlin yang di gunakan di aplikasi SoalUTSPersegiPanjang.
#### MainActivity.kt
```
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_hitung.setOnClickListener {
            val hitungIntent = Intent(this@MainActivity, HitungActivity::class.java)
            startActivity(hitungIntent)
        }

        btn_detail.setOnClickListener {
            val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
            startActivity(detailIntent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if ( id == R.id.menu_profile)  {
            val profileIntent = Intent(this@MainActivity, ProfileActivity::class.java)
            startActivity(profileIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}
```
#### HitungActivity.kt
```
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
```

#### DetailActivity.kt
```
class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_main)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
```
#### ProfileActivity.kt
```
class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_main)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
```
