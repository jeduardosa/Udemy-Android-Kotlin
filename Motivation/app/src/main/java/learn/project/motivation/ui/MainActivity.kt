package learn.project.motivation.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import learn.project.motivation.infra.MotivationConstants
import learn.project.motivation.R
import learn.project.motivation.infra.SecurityPreferences
import learn.project.motivation.databinding.ActivityMainBinding
import androidx.core.content.ContextCompat
import learn.project.motivation.data.Mock


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var filter: Int = MotivationConstants.PHRASEFILTER.ALL
    private var mock: Mock = Mock()
    private lateinit var securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Esconde barra de navegação
        supportActionBar?.hide()

        securityPreferences = SecurityPreferences(this)

        //Adiciona eventos
        setListeners()

        //inicializa
        handleFilter(R.id.image_all)
        refreshPhrase()

    }

    override fun onResume() {
        super.onResume()
        showUserName()
    }

    override fun onClick(view: View) {
        val id: Int = view.id

        val listId = listOf(
            R.id.image_all,
            R.id.image_happy,
            R.id.image_sunny
        )
        if (id in listId) {
            handleFilter(id)
        } else if (id == R.id.button_new_phrase) {
            refreshPhrase()
        } else if (id == R.id.text_user_name) {
            startActivity(Intent(this, UserActivity::class.java))
        }
    }

    //Eventos
    private fun setListeners() {
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.textUserName.setOnClickListener(this)
    }

    private fun refreshPhrase() {
        binding.textPhrase.text = mock.getPhrase(filter)
    }

    private fun showUserName() {
        val name = securityPreferences.getStoredString(MotivationConstants.KEY.PERSON_NAME)
        binding.textUserName.text = "Olá, $name!"
    }

    private fun handleFilter(id: Int) {
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_all -> {
                filter = MotivationConstants.PHRASEFILTER.ALL
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }
            R.id.image_happy -> {
                filter = MotivationConstants.PHRASEFILTER.HAPPY
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }
            R.id.image_sunny -> {
                filter = MotivationConstants.PHRASEFILTER.SUNNY
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }
        }
    }
}