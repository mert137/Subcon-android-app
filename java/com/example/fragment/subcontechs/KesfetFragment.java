package com.example.fragment.subcontechs;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class KesfetFragment extends Fragment {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    LinkedHashMap<String, List<String>> expandableListDetail;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {




        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.kesfet_main, container, false);

        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);

        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());

        expandableListAdapter = new CustomExpandableListAdapter(getContext(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                YeniKapi ldf = new YeniKapi ();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.mainLayout, ldf)  // content_main.xml deki frame layout id'si
                        .addToBackStack(null)
                        .commit();

                return false;
            }
        });





        return view;
    }








    public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

        private Context context;
        private List<String> expandableListTitle;
        private LinkedHashMap<String, List<String>> expandableListDetail;

        public CustomExpandableListAdapter(Context context, List<String> expandableListTitle,
                                           LinkedHashMap<String, List<String>> expandableListDetail
                                            ) {
            this.context = context;
            this.expandableListTitle = expandableListTitle;
            this.expandableListDetail = expandableListDetail;
        }

        @Override
        public Object getChild(int listPosition, int expandedListPosition) {
            return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                    .get(expandedListPosition);
        }

        @Override
        public long getChildId(int listPosition, int expandedListPosition) {
            return expandedListPosition;
        }

        @Override
        public View getChildView(int listPosition, final int expandedListPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            final String expandedListText = (String) getChild(listPosition, expandedListPosition);

            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.kesfet_list_item, null);
            }
            TextView expandedListTextView = (TextView) convertView
                    .findViewById(R.id.expandedListItem);
            expandedListTextView.setText(expandedListText);
            return convertView;
        }

        @Override
        public int getChildrenCount(int listPosition) {
            return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                    .size();
        }

        @Override
        public Object getGroup(int listPosition) {
            return this.expandableListTitle.get(listPosition);
        }

        @Override
        public int getGroupCount() {
            return this.expandableListTitle.size();
        }

        @Override
        public long getGroupId(int listPosition) {
            return listPosition;
        }

        @Override
        public View getGroupView(int listPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            String listTitle = (String) getGroup(listPosition);
            TypedArray listThumbnail = getResources().obtainTypedArray(R.array.kesfet_durak_thumbnails);

            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.context.
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.kesfet_list_group, null);
            }
            TextView listTitleTextView = (TextView) convertView
                    .findViewById(R.id.listTitle);
            listTitleTextView.setText(listTitle);

            ImageView listThumbnail1 = convertView.findViewById(R.id.kesfet_durak_image);
            listThumbnail1.setImageResource(listThumbnail.getResourceId(listPosition,0));
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int listPosition, int expandedListPosition) {
            return true;
        }
    }






}