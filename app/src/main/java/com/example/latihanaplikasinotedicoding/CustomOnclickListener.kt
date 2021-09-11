package com.example.latihanaplikasinotedicoding

import android.view.View

class CustomOnclickListener(
    private val position: Int,
    private val onItemClickCallback: OnItemClickCallback
) : View.OnClickListener {

//    Kelas di atas bertugas membuat item seperti CardView bisa diklik di dalam adapter.

    override fun onClick(p0: View?) {
        onItemClickCallback.onItemClicked(p0, position)
    }

    interface OnItemClickCallback {
        fun onItemClicked(view: View?, position: Int)
    }
}