package com.pranoy.lowcostpath;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pranoy on 12/15/2017.
 */
public class LowCostPathFragment extends Fragment {

    private GridCreator loadedGrid;

    public LowCostPathFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_lowest_cost_examples_main, container, false);

        Button gridOneButton = (Button) fragmentView.findViewById(R.id.grid_1_button);
        gridOneButton.setOnClickListener(new GridOnClickListener());
        Button gridTwoButton = (Button) fragmentView.findViewById(R.id.grid_2_button);
        gridTwoButton.setOnClickListener(new GridOnClickListener());
        Button gridThreeButton = (Button) fragmentView.findViewById(R.id.grid_3_button);
        gridThreeButton.setOnClickListener(new GridOnClickListener());
        Button gridFourButton = (Button) fragmentView.findViewById(R.id.grid_4_button);
        gridFourButton.setOnClickListener(new GridOnClickListener());
        Button gridFiveButton = (Button) fragmentView.findViewById(R.id.grid_5_button);
        gridFiveButton.setOnClickListener(new GridOnClickListener());
        Button gridSixButton = (Button) fragmentView.findViewById(R.id.grid_6_button);
        gridSixButton.setOnClickListener(new GridOnClickListener());
        Button gridSevenButton = (Button) fragmentView.findViewById(R.id.grid_7_button);
        gridSevenButton.setOnClickListener(new GridOnClickListener());
        Button gridEightButton = (Button) fragmentView.findViewById(R.id.grid_8_button);
        gridEightButton.setOnClickListener(new GridOnClickListener());
        Button gridNineButton = (Button) fragmentView.findViewById(R.id.grid_9_button);
        gridNineButton.setOnClickListener(new GridOnClickListener());
        Button gridTenButton = (Button) fragmentView.findViewById(R.id.grid_10_button);
        gridTenButton.setOnClickListener(new GridOnClickListener());
        Button gridElevenButton = (Button) fragmentView.findViewById(R.id.grid_11_button);
        gridElevenButton.setOnClickListener(new GridOnClickListener());
        Button gridTwelveButton = (Button) fragmentView.findViewById(R.id.grid_12_button);
        gridTwelveButton.setOnClickListener(new GridOnClickListener());
        Button gridThirteenButton = (Button) fragmentView.findViewById(R.id.grid_13_button);
        gridThirteenButton.setOnClickListener(new GridOnClickListener());


        Button goButton = (Button) fragmentView.findViewById(R.id.go_button);
        goButton.setOnClickListener(new GoOnClickListener());
        return fragmentView;
    }

    private GridCreator getGridForButton(View view) {
        switch (view.getId()) {
            case R.id.grid_1_button:
                return GridUtils.EXAMPLE_GRID_1;
            case R.id.grid_2_button:
                return GridUtils.EXAMPLE_GRID_2;
            case R.id.grid_3_button:
                return GridUtils.EXAMPLE_GRID_3;
            case R.id.grid_4_button:
                return GridUtils.EXAMPLE_GRID_4;
            case R.id.grid_5_button:
                return GridUtils.SAMPLE_GRID_5;
            case R.id.grid_6_button:
                return GridUtils.SAMPLE_GRID_6;
            case R.id.grid_7_button:
                return GridUtils.SAMPLE_GRID_7;
            case R.id.grid_8_button:
                return GridUtils.SAMPLE_GRID_8;
            case R.id.grid_9_button:
                return GridUtils.SAMPLE_GRID_9;
            case R.id.grid_10_button:
                return GridUtils.SAMPLE_GRID_10;
            case R.id.grid_11_button:
                return GridUtils.SAMPLE_GRID_11;
            case R.id.grid_12_button:
                return GridUtils.SAMPLE_GRID_12;
            case R.id.grid_13_button:
                return GridUtils.SAMPLE_GRID_13;

            default:
                return null;
        }
    }

    private void clearResults() {
        ((TextView) getView().findViewById(R.id.results_success)).setText("");
        ((TextView) getView().findViewById(R.id.results_total_cost)).setText(getResources().getText(R.string.no_results));
        ((TextView) getView().findViewById(R.id.results_path_taken)).setText("");
    }

    private String formatPath(PathState path) {
        StringBuilder builder = new StringBuilder();
        List<Integer> rows = path.getRowsTraversed();

        for (int i = 0; i < rows.size(); i++) {
            builder.append(rows.get(i));
            if (i < rows.size() - 1) {
                builder.append("\t");
            }
        }

        return builder.toString();
    }

    class GridOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            GridCreator selectedGrid = getGridForButton(view);
            if(selectedGrid.containsStringValues())
            {
                if(!selectedGrid.isValidValues())
                {
                    clearResults();
                    ((TextView) getView().findViewById(R.id.results_success)).setText("Invalid Matrix");
                    return;

                }


            }
            if (selectedGrid != null && !selectedGrid.equals(loadedGrid)) {
                clearResults();
            }

            loadedGrid = selectedGrid;
            ((TextView) getView().findViewById(R.id.grid_contents)).setText(loadedGrid.asDelimitedString("\t"));
            getView().findViewById(R.id.go_button).setEnabled(true);
        }
    }

    class GoOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            GridValidator visitor = new GridValidator(loadedGrid);
            PathState bestPath = visitor.getBestPathForGrid();


            if (bestPath.isSuccessful() && !bestPath.isEmpty()) {
                ((TextView) getView().findViewById(R.id.results_success)).setText("Yes");
            } else if (!bestPath.isEmpty()){
                ((TextView) getView().findViewById(R.id.results_success)).setText("No");
            }else {
                ((TextView) getView().findViewById(R.id.results_success)).setText("Invalid Matrix");
            }
            ((TextView) getView().findViewById(R.id.results_total_cost)).setText(Integer.toString(bestPath.getTotalCost()));
            ((TextView) getView().findViewById(R.id.results_path_taken)).setText(formatPath(bestPath));
        }
    }
}
