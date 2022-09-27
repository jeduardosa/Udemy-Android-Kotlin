package learn.project.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import learn.project.motivation.infra.MotivationConstants
import learn.project.motivation.R
import learn.project.motivation.infra.SecurityPreferences
import learn.project.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding
    private lateinit var securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        securityPreferences = SecurityPreferences(this)

        binding.buttonSave.setOnClickListener(this)


    }
    override fun onClick(view: View?) {
        val id: Int? = view?.id
        if (id == R.id.button_save) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name: String = binding.editName.text.toString()

        if (name == ""){
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_LONG)
                .show()
        } else {
            securityPreferences.storeString(MotivationConstants.KEY.PERSON_NAME, name)
            finish()
        }
    }

}