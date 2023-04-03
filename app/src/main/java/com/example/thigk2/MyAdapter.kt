package com.example.thigk2
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.list_item.view.*



class MyAdapter(var context: Context, val userlist: ArrayList<OutData>) :RecyclerView.Adapter<MyAdapter.MyViewHolder>(){


    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        /* Set Menu*/
        init {
            val mMenu = itemView.findViewById<ImageView>(R.id.mMenu)
            mMenu.setOnClickListener {
                PopupMenus(it)
            }
        }

        @SuppressLint("MissingInflatedId")
        private fun PopupMenus(itemView: View) {
            val p0 = userlist[adapterPosition]
            val PopupMenus = PopupMenu(context, itemView)
            PopupMenus.inflate(R.menu.show)
            PopupMenus.setOnMenuItemClickListener {
                when (it.itemId) {
        // SET ADDCONTACT
                    R.id.AddContact ->{

                        val itemView = LayoutInflater.from(context).inflate(R.layout.activity_main, null)

                        val edtName = itemView.findViewById<EditText>(R.id.edtname)
                        val edtphone = itemView.findViewById<EditText>(R.id.edtphone)
                        val edtemail = itemView.findViewById<EditText>(R.id.edtemail)

                        val name = userlist[adapterPosition].name
                        val phone = userlist[adapterPosition].phone
                        val email = userlist[adapterPosition].email


                        val id = userlist[adapterPosition].id
                        edtName.setText("")
                        edtphone.setText("")
                        edtemail.setText("")

                        val addDialog = AlertDialog.Builder(context)
                            .setView(itemView)
                            .setPositiveButton("OK") { dialog, _ ->

                                val db = DBHelper(context)
                                val name = edtName.text.toString()
                                val phone = edtphone.text.toString()
                                val email = edtemail.text.toString()

                                db.savedata(  name, phone, email)

                                Toast.makeText(context, "Add contact thành công", Toast.LENGTH_SHORT).show()

                                userlist.add(OutData( id, name, phone, email))
                                notifyDataSetChanged()
                                dialog.dismiss()
                            }
                            .setNegativeButton("Cancel") { dialog, _ ->
                                Toast.makeText(context, "Cancel", Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .create()
                            .show()
                        true
                                      }



        /*SET Edit */
                    R.id.Edit -> {
                        val itemView = LayoutInflater.from(context).inflate(R.layout.activity_main, null)

                        val edtName = itemView.findViewById<EditText>(R.id.edtname)
                        val edtphone = itemView.findViewById<EditText>(R.id.edtphone)
                        val edtemail = itemView.findViewById<EditText>(R.id.edtemail)

                        val name = userlist[adapterPosition].name
                        val phone = userlist[adapterPosition].phone
                        val email = userlist[adapterPosition].email


                        val id = userlist[adapterPosition].id
                            edtName.setText(name)
                            edtphone.setText(phone)
                            edtemail.setText(email)

                        val addDialog = AlertDialog.Builder(context)
                                .setView(itemView)
                                .setPositiveButton("OK") { dialog, _ ->

                            val db = DBHelper(context)
                            val name = edtName.text.toString()
                            val phone = edtphone.text.toString()
                            val email = edtemail.text.toString()
                           db.update( id, name, phone, email)
                                    val index = userlist.indexOfFirst { it.id == id }
                                    if (index >= 0) {
                                        // update the item at the index with the updated data
                                        userlist[index].name = name
                                        userlist[index].phone = phone
                                        userlist[index].email = email
                                        notifyItemChanged(index)
                                    }
                                    Toast.makeText(context, "Edit thành công", Toast.LENGTH_SHORT).show()
                           // notifyDataSetChanged()
                            dialog.dismiss()
                        }
                            .setNegativeButton("Cancel") { dialog, _ ->
                            Toast.makeText(context, "Cancel", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                         true
         /* SET EDIT*/               }

        /* Set delete*/
                    R.id.Delete -> {
                        val addDialog = AlertDialog.Builder(context)
                            .setTitle("Delete")
                            .setIcon(R.drawable.ic_baseline_warning_24)
                            .setMessage("Are you sure delete this information delete")
                            .setPositiveButton("YES") { dialog, _ ->
                                val db = DBHelper(context)
                                db.deleteData(userlist[adapterPosition].name)
                                userlist.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("No") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()

                        addDialog.show()
                        true
                    }
                    else ->  true
                }
         /* Set delete*/
            }
            PopupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(PopupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu,true)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {

        val itemView = LayoutInflater.from(p0.context).inflate(R.layout.list_item, p0, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.itemView.apply{
            txtname.text = userlist[p1].name
            txtphones.text = userlist[p1].phone
            txtemails.text = userlist[p1].email
        }
    }
    override fun getItemCount(): Int {
        return userlist.size
    }
}

