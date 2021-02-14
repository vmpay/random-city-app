package eu.vmpay.random.city.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.vmpay.random.city.databinding.ItemCityBinding
import eu.vmpay.random.city.model.CityModel
import eu.vmpay.random.city.tools.getFixedColor
import eu.vmpay.random.city.tools.sdf
import java.util.*

class CityListAdapter(private val onClick: (CityModel) -> Unit) : ListAdapter<CityModel, CityListAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CityModel>() {
            override fun areItemsTheSame(oldItem: CityModel, newItem: CityModel) = oldItem.uid == newItem.uid
            override fun areContentsTheSame(oldItem: CityModel, newItem: CityModel) = oldItem == newItem
        }
    }

    class ViewHolder(private val itemCityBinding: ItemCityBinding) : RecyclerView.ViewHolder(itemCityBinding.root) {
        fun bind(item: CityModel, onClick: (CityModel) -> Unit) {
            itemCityBinding.apply {
                root.setOnClickListener { onClick(item) }
                tvTitle.apply {
                    text = item.title
                    setTextColor(item.color.getFixedColor())
                }
                tvDate.text = sdf.format(Date(item.timestamp))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }
}
