package eu.vmpay.random.city.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

import eu.vmpay.random.city.databinding.ItemCityBinding;
import eu.vmpay.random.city.model.CityModel;
import eu.vmpay.random.city.tools.ColorUtils;

import static eu.vmpay.random.city.tools.ConstantsKt.getSdf;

/**
 * Java class example
 */
public class CityListAdapterJ extends ListAdapter<CityModel, CityListAdapterJ.ViewHolder> {

    public interface IOnClick {
        void click(CityModel cityModel);
    }

    static private final DiffUtil.ItemCallback<CityModel> diffCallback = new DiffUtil.ItemCallback<CityModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull CityModel oldItem, @NonNull CityModel newItem) {
            return oldItem.getUid() == newItem.getUid();
        }

        @Override
        public boolean areContentsTheSame(@NonNull CityModel oldItem, @NonNull CityModel newItem) {
            return oldItem.equals(newItem);
        }
    };

    private IOnClick onClick;

    public CityListAdapterJ(IOnClick onClick) {
        super(CityListAdapterJ.diffCallback);
        this.onClick = onClick;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemCityBinding binding;

        public ViewHolder(@NonNull ItemCityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(CityModel item, IOnClick onClick) {
            binding.tvTitle.setText(item.getTitle());
            binding.tvTitle.setTextColor(ColorUtils.getFixedColor(item.getColor()));
            binding.tvDate.setText(getSdf().format(new Date(item.getTimestamp())));
            binding.getRoot().setOnClickListener(v -> onClick.click(item));
        }
    }

    @NonNull
    @Override
    public CityListAdapterJ.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCityBinding binding = ItemCityBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CityListAdapterJ.ViewHolder holder, int position) {
        holder.bind(getItem(position), onClick);
    }
}
