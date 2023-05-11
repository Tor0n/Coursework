import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.EmployeeList
import com.example.myapplication.IntentConstants
import com.example.myapplication.R
import com.example.myapplication.databinding.RcItemBinding
import com.example.myapplication.for_editing.EditActivity

class RcAdapter(arrayList1: ArrayList<EmployeeList>, contextM: Context): RecyclerView.Adapter<RcAdapter.ViewHolder>() {
    private var nameList = arrayList1
    private var context = contextM

    class ViewHolder(itemView: View, contextV: Context) : RecyclerView.ViewHolder(itemView) {
        private val binding = RcItemBinding.bind(itemView)
        private val context = contextV

        fun setData(item: EmployeeList) {
            var onLongClickListenerActive = false
            binding.empNameRc.text = item.name
            Glide.with(context).load(item.url).into(binding.empImageRc)
            //binding.empImageRc.setImageURI(Url.parse(item.url))
            itemView.setOnClickListener {
                if (!onLongClickListenerActive) {
                    val intent = Intent(context, EditActivity::class.java).apply {
                        putExtra(IntentConstants.I_NAME_KEY, item.name)
                        putExtra(IntentConstants.I_SALARY_KEY, item.salary)
                        putExtra(IntentConstants.I_URL_KEY, item.url)
                        putExtra(IntentConstants.I_ID_KEY, item.id)
                        putExtra(IntentConstants.I_EMP_STATUS_KEY, true)
                    }
                    context.startActivity(intent)
                } else {
                    binding.itemChecked.visibility = View.VISIBLE
                }
            }
            itemView.setOnLongClickListener {
                onLongClickListenerActive = true
                binding.conCurtain.visibility = View.VISIBLE
                return@setOnLongClickListener true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.rc_item, parent, false), context)
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(nameList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(itemList: ArrayList<EmployeeList>) {
        nameList.clear()
        nameList.addAll(itemList)
        notifyDataSetChanged()
    }

}