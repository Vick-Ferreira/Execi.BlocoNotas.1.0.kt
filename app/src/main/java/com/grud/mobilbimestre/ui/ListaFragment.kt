package com.grud.mobilbimestre.ui

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.grud.mobilbimestre.R
import com.grud.mobilbimestre.databinding.FragmentListaBinding


class ListaFragment : Fragment() {
    //configuração iniciar do Binding
    private var _binding: FragmentListaBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: AdapterLista
    private lateinit var sharedPreferences: SharedPreferences
    private val lista = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListaBinding.inflate(inflater, container, false)

        val view = binding.root

        sharedPreferences = requireContext().getSharedPreferences(
            "MinhaListaSharedPreferences",
            Context.MODE_PRIVATE
        )

        val menuButton = view.findViewById<ImageView>(R.id.menu_button)
        val popupMenu = PopupMenu(requireContext(), menuButton)

        // Inflar o menu no PopupMenu
        popupMenu.menuInflater.inflate(R.menu.menu, popupMenu.menu)

         // Configurar um ouvinte de clique para o ImageView
        menuButton.setOnClickListener {
            // Mostrar o PopupMenu na posição do ImageView
            popupMenu.show()
        }

         // Lidar com as ações do menu quando um item é clicado
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_item1 -> {
                    // Lógica para ação do item 1
                    true
                }
                R.id.menu_item2 -> {
                    // Lógica para ação do item 2
                    true
                }
                else -> false
            }
        }


        // Carrega a lista salva no SharedPreferences
        lista.addAll(loadListFromSharedPreferences())

        initRecycleView()
        setClickListeners()

        return view
    }




    private fun initRecycleView() {
        adapter = AdapterLista(requireContext(), lista) { name ->
            // Item da lista clicado, você pode adicionar alguma ação aqui
        }
        binding.recyclerLista.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerLista.adapter = adapter
    }

    private fun setClickListeners() {
        binding.btnAdd.setOnClickListener {
            showAddDialog()
        }
    }

    private fun showAddDialog() {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setTitle("Adicionar Tarefa")

        val editText = EditText(requireContext())
        dialogBuilder.setView(editText)

        dialogBuilder.setPositiveButton("Adicionar") { _, _ ->
            val newName = editText.text.toString()
            if (newName.isNotEmpty()) {
                // Adicione a nova tarefa à lista
                lista.add(newName)
                adapter.notifyDataSetChanged()

                // Salve a lista atualizada no SharedPreferences
                saveListToSharedPreferences(lista)
            }
        }
        dialogBuilder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    private fun loadListFromSharedPreferences(): MutableList<String> {
        val listaString = sharedPreferences.getString("MinhaListaKey", null)
        val lista = listaString?.split(",")?.toMutableList() ?: mutableListOf()

        // Remova tarefas vazias, se houver
        lista.removeAll { it.isBlank() }

        return lista
    }
    private fun saveListToSharedPreferences(lista: List<String>) {
        val listaString = lista.joinToString(",")
        sharedPreferences.edit().putString("MinhaListaKey", listaString).apply()
    }

}