package com.example.latihanaplikasinotedicoding

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.latihanaplikasinotedicoding.databinding.ItemNoteBinding

class Adapter(private val activity: Activity) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    var listNotes = ArrayList<Note>()
        @SuppressLint("NotifyDataSetChanged")
        set(listNotes) {
            if (listNotes.size > 0) {
                this.listNotes.clear()
            }

            this.listNotes.addAll(listNotes)
            notifyDataSetChanged()
        }

    fun addItem(note: Note) {
        this.listNotes.add(note)
        notifyItemInserted(this.listNotes.size - 1)
    }

    fun updateItem(position: Int, note: Note) {
        this.listNotes[position] = note
        notifyItemChanged(position, note)
    }

    fun removeItem(position: Int) {
        this.listNotes.removeAt(position)
        notifyItemChanged(position)
        notifyItemRangeChanged(position, this.listNotes.size)
    }

    //poin 13
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemNoteBinding.bind(itemView)

        fun bind(note: Note) {
            binding.tvItemTitle.text = note.title
            binding.tvItemDate.text = note.dat
            binding.tvItemDescription.text = note.description

            binding.cvItemNote.setOnClickListener {
                CustomOnclickListener(
                    adapterPosition,
                    object : CustomOnclickListener.OnItemClickCallback {
                        override fun onItemClicked(view: View?, position: Int) {
                            val intent = Intent(activity, NoteAddUpdateActivity::class.java)
                            intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, position)
                            intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
                            activity.startActivityForResult(
                                intent,
                                NoteAddUpdateActivity.REQUEST_UPDATE
                            )
                            Toast.makeText(activity, "Berjalan", Toast.LENGTH_SHORT).show()
                        }

                    })
            }
        }
    }

    override fun getItemCount(): Int {
        return this.listNotes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }
}