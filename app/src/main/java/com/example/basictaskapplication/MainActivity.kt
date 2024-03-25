package com.example.basictaskapplication

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.basictaskapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener {
            showTitleDialog()
        }
    }

    private fun showTitleDialog() {
        val popupTitulo = AlertDialog.Builder(this)
        val textTitulo = EditText(this)

        popupTitulo.setTitle("Título")
        popupTitulo.setView(textTitulo)
        popupTitulo.setPositiveButton("Ok") { dialog, _ ->
            val titulo = textTitulo.text.toString()
            showDescriptionDialog(titulo)
        }
        popupTitulo.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.cancel()
        }
        popupTitulo.show()
    }

    private fun showDescriptionDialog(titulo: String) {
        val popupDescricao = AlertDialog.Builder(this)
        val textDescricao = EditText(this)

        popupDescricao.setTitle("Descrição")
        popupDescricao.setView(textDescricao)
        popupDescricao.setPositiveButton("Ok") { dialog, which ->
            val descricao = textDescricao.text.toString()
            showStatusDialog(titulo, descricao)
        }
        popupDescricao.setNegativeButton("Cancelar") { dialog, which ->
            dialog.cancel()
        }
        popupDescricao.show()
    }

    private fun showStatusDialog(titulo: String, descricao: String) {
        val statusOptions = arrayOf("Pendente", "Concluída")

        val popupStatus = AlertDialog.Builder(this)
        popupStatus.setTitle("Status")
        popupStatus.setItems(statusOptions) { dialog, which ->
            val status = statusOptions[which]
            val taskStatus = if (status == "Concluída") "Concluída" else "Pendente"
            val task = Task(titulo, descricao, taskStatus)
            // Adicionar task ao vetor de tasks aqui
            Snackbar.make(binding.root, "Tarefa criada com sucesso: $task", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        popupStatus.show()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}