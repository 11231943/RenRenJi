package com.trade.rrenji.fragment;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;


import com.trade.rrenji.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract   class RecyclerListAdapter<T> extends RecyclerView.Adapter<RecyclerListAdapter.ViewHolder> {

    private Context mContext;
    protected List<T> mDataSet;

    protected OnItemClickListener<T> mOnItemClickListener;
    protected OnItemLongClickListener<T> mOnItemLongClickListener;

    public interface OnItemClickListener<T> {
        void onItemClick(View v, T data);
    }

    public interface OnItemLongClickListener<T> {
        boolean onItemLongClick(View v, T data);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public abstract class ViewHolder extends RecyclerView.ViewHolder {
        View mRootView;
        T mData;

        public ViewHolder(View itemView) {
            super(itemView);
            mRootView = itemView;
            mRootView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null && mData != null) {
                        mOnItemClickListener.onItemClick(v, mData);
                    }
                }
            });
            mRootView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mOnItemLongClickListener != null && mData != null) {
                        return mOnItemLongClickListener.onItemLongClick(v, mData);
                    }
                    return false;
                }
            });
        }

        public void bindData(T data, int position) {
            mData = data;
        }
    }

    public class DummyViewHolder extends ViewHolder {

        public DummyViewHolder() {
            super(new View(getContext()));
        }

        @Override
        public void bindData(T data, int position) {
            super.bindData(data, position);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerListAdapter(Context context, List<T> myDataSet) {
        mDataSet = myDataSet == null ? new ArrayList<T>() : myDataSet;
        mContext = context;
    }

    public RecyclerListAdapter(Context context) {
        this(context, new ArrayList<T>());
    }

    public void add(T item) {
        int pos = getItemCount();
        if (mDataSet.add(item)) {
            notifyItemInserted(pos);
        }
    }

    /**
     * Adds the specified items at the end of the array.
     *
     * @param items The items to add at the end of the array.
     */
    public void addAll(Collection<T> items) {
        int pos = getItemCount();
        if (CollectionUtils.addAll(mDataSet, items)) {
            notifyItemRangeInserted(pos, items.size());
        }
    }

    public void addAll(int index, Collection<T> items) {
        if (mDataSet.addAll(index, items)) {
            notifyItemRangeInserted(index, CollectionUtils.getSize(items));
        }
    }

    public void addAllWithoutAnim(Collection<T> items) {
        if (CollectionUtils.addAll(mDataSet, items)) {
            notifyDataSetChanged();
        }
    }

    public void addAllWithoutAnim(T... items) {
        if (CollectionUtils.addAll(mDataSet, items)) {
            notifyDataSetChanged();
        }
    }

    public void add(int index, T object) {
        mDataSet.add(index, object);
        notifyDataSetChanged();
    }

    /**
     * Inserts the specified object at the specified index in the array.
     *
     * @param object The object to insert into the array.
     * @param index  The index at which the object must be inserted.
     */
    public void insert(int index, T object) {
        mDataSet.add(index, object);
        notifyItemInserted(index);
    }

    /**
     * Removes the specified object from the array.
     *
     * @param object The object to remove.
     */
    public void remove(T object) {
        int pos = mDataSet.indexOf(object);
        if (pos != -1) {
            mDataSet.remove(pos);
            notifyItemRemoved(pos);
        }
    }

    /**
     * Removes the specified object at pos.
     *
     * @param pos The pos of the object to remove.
     */
    public void remove(int pos) {
        if (pos >= 0 && pos < getItemCount()) {
            mDataSet.remove(pos);
            notifyItemRemoved(pos);
        }
    }

    /**
     * Remove all elements from the list.
     */
    public void clear() {
        mDataSet.clear();
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        if (position < 0 || position >= getItemCount()) {
            return null;
        }

        return mDataSet.get(position);
    }

    public T getLastItem() {
        return getItem(getItemCount() - 1);
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public final void onBindViewHolder(RecyclerListAdapter.ViewHolder holder, int position) {
        holder.bindData(getItem(position), position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return CollectionUtils.getSize(mDataSet);
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public Context getContext() {
        return mContext;
    }

    /**
     * return a copy of data set in adapter
     * @return a copy of the data set
     */
    public ArrayList<T> getDataSet() {
        ArrayList<T> list = new ArrayList<>(mDataSet);
        return list;
    }

    public int getChildPosition(T child) {
        return mDataSet.indexOf(child);
    }

    protected <V extends View> V inflate(ViewGroup parent, @LayoutRes int layoutRes) {
        return (V) LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
    }
}
