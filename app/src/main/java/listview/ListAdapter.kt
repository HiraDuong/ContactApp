package listview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.example.contactapp.R

class ListAdapter(context: Context, val dataArrayList: ArrayList<UserItem?>) :
    ArrayAdapter<UserItem?>(context, R.layout.items_user, dataArrayList!!), Filterable {

/// test List Filter


    ////////
    // Danh sách nguyên bản trước khi lọc
    var dataUserListFilter = ArrayList<UserItem>()
    init {
        dataUserListFilter = dataArrayList as ArrayList<UserItem>
    }





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

    override fun getFilter(): Filter {

        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                Log.v("TAG-data-old",dataUserListFilter.toString())
                //Log.v("TAG-data",dataUserList.toString())
                val charSearch = constraint.toString()

                if (charSearch.isEmpty()){
                    dataUserListFilter = dataArrayList as ArrayList<UserItem>
                }
                else{
                    val filteredList = ArrayList<UserItem>()
                    for (user in dataArrayList) {
                        if (constraint?.toString()?.let { user?.name?.contains(it, true) } == true) {
                            if (user != null) {
                                filteredList.add(user)
                            }
                        }
                    }
                    dataUserListFilter = filteredList

                }
                val filterResults = FilterResults()
                filterResults.values = dataUserListFilter
                Log.v("TAG-filter-result",dataUserListFilter.joinToString { it?.name ?: "null" })
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                dataUserListFilter = results?.values as ArrayList<UserItem>
                Log.d("TAG-filter-result", dataUserListFilter.joinToString { it?.name ?: "null" })
                notifyDataSetChanged()

//                if (results?.values != null) {
//                    clear()
//                    addAll(results.values as List<UserItem?>)
//                    Log.d("TAG-filter-result", dataUserListFilter.joinToString { it?.name ?: "null" })
//                    notifyDataSetChanged() // Highlighted change
//                }

            }


        }
    }
}