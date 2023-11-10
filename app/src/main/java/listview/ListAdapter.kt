package listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.contactapp.R

class ListAdapter(context: Context, dataArrayList: ArrayList<UserItem?>) :
    ArrayAdapter<UserItem?>(context, R.layout.items_user, dataArrayList!!){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val listData = getItem(position)

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.items_user,parent,false)

        }

        val avtUser = view!!.findViewById<ImageView>(R.id.avt_user)
        val nameUser = view.findViewById<TextView>(R.id.name)

        avtUser.setImageResource(listData!!.avt)
        nameUser.text = listData.name

        return view
    }
}