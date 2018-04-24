package io.github.andylx96.gilsonapi;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.CatmullRomInterpolator;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.ScalingXYSeries;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.google.gson.Gson;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewAllRunData extends AppCompatActivity {
    private static final String TAG = "ViewAllRunData";
    DataBaseHelper myDb;

    private ListView lv;
    private ArrayAdapter arrayAdapter;
    private AdapterView.AdapterContextMenuInfo info;
    Double[] tempAccelArrayX;
    Double[] tempAccelArrayY;
    Double[] tempAccelArrayZ;
    Double[] tempGyroArrayX;
    Double[] tempGyroArrayY;
    Double[] tempGyroArrayZ;
    Double[] tempTempArray;
    XYPlot plot;
    View mView;
    AlertDialog.Builder mBuilder;
    final Number[] domainLabels = {0, 1, 2, 3, 6, 7, 8, 9, 10, 13, 14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        this.getMenuInflater().inflate(R.menu.list_view_menu, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_view_menu, menu);
        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        ValueAnimator animator1;
        ValueAnimator animator2;
        ValueAnimator animator3;
        ValueAnimator animator4;
        ValueAnimator animator5;
        ValueAnimator animator6;
        ValueAnimator animator7;
        ViewGroup parentViewGroup;
        switch (item.getItemId()) {
            case R.id.AccelX:

                plot.clear();

                XYSeries series1 = new SimpleXYSeries(
                        Arrays.asList(tempAccelArrayX), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Accel X");


                LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.RED, Color.GREEN, Color.BLUE, null);

                // see: http://androidplot.com/smooth-curves-and-androidplot/
                series1Format.setInterpolationParams(
                        new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));


                // wrap each series in instances of ScalingXYSeries before adding to the plot
                // so that we can animate the series values below:
                final ScalingXYSeries scalingSeries1 = new ScalingXYSeries(series1, 0, ScalingXYSeries.Mode.Y_ONLY);
                plot.addSeries(scalingSeries1, series1Format);


//                plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
//                    @Override
//                    public StringBuffer format(Object obj,
//                                               @NonNull StringBuffer toAppendTo,
//                                               @NonNull FieldPosition pos) {
//                        int i = Math.round(((Number) obj).floatValue());
//                        return toAppendTo.append(domainLabels[i]);
//                    }
//
//                    @Override
//                    public Object parseObject(String source, @NonNull ParsePosition pos) {
//                        return null;
//                    }
//                });

//                plot.setRangeBoundaries(-1, 1, BoundaryMode.FIXED);

                // animate a scale value from a starting val of 0 to a final value of 1:
                animator1 = ValueAnimator.ofFloat(0, 1);

                // use an animation pattern that begins and ends slowly:
                animator1.setInterpolator(new AccelerateDecelerateInterpolator());

                animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float scale = valueAnimator.getAnimatedFraction();
                        scalingSeries1.setScale(scale);
                        plot.redraw();
                    }
                });
                animator1.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        // the animation is over, so show point labels:
                        series1Format.getPointLabelFormatter().getTextPaint().setColor(Color.WHITE);
                        plot.redraw();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                parentViewGroup = (ViewGroup) mView.getParent();
                if (parentViewGroup != null) {
                    parentViewGroup.removeAllViews();
                }
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

                // the animation will run for 1.5 seconds:
                animator1.setDuration(1500);
                animator1.start();


                return true;

            case R.id.AccelY:

                plot.clear();

                XYSeries series2 = new SimpleXYSeries(
                        Arrays.asList(tempAccelArrayY), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Accel Y");


                LineAndPointFormatter series2Format = new LineAndPointFormatter(Color.RED, Color.GREEN, Color.BLUE, null);


                series2Format.setInterpolationParams(
                        new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));


                final ScalingXYSeries scalingSeries2 = new ScalingXYSeries(series2, 0, ScalingXYSeries.Mode.Y_ONLY);
                plot.addSeries(scalingSeries2, series2Format);


//                plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
//                    @Override
//                    public StringBuffer format(Object obj,
//                                               @NonNull StringBuffer toAppendTo,
//                                               @NonNull FieldPosition pos) {
//                        int i = Math.round(((Number) obj).floatValue());
//                        return toAppendTo.append(domainLabels[i]);
//                    }
//
//                    @Override
//                    public Object parseObject(String source, @NonNull ParsePosition pos) {
//                        return null;
//                    }
//                });
//
//                plot.setRangeBoundaries(-1, 1, BoundaryMode.FIXED);

                // animate a scale value from a starting val of 0 to a final value of 1:
                animator2 = ValueAnimator.ofFloat(0, 1);

                // use an animation pattern that begins and ends slowly:
                animator2.setInterpolator(new AccelerateDecelerateInterpolator());

                animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float scale = valueAnimator.getAnimatedFraction();

                        scalingSeries2.setScale(scale);

                        plot.redraw();
                    }
                });
                animator2.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        // the animation is over, so show point labels:

                        series2Format.getPointLabelFormatter().getTextPaint().setColor(Color.WHITE);
                        plot.redraw();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                parentViewGroup = (ViewGroup) mView.getParent();
                if (parentViewGroup != null) {
                    parentViewGroup.removeAllViews();
                }
                mBuilder.setView(mView);
                AlertDialog dialog2 = mBuilder.create();
                dialog2.show();

                // the animation will run for 1.5 seconds:
                animator2.setDuration(1500);
                animator2.start();


                return true;

            case R.id.AccelZ:
                plot.clear();

                XYSeries series3 = new SimpleXYSeries(
                        Arrays.asList(tempAccelArrayZ), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Accel Z");


                LineAndPointFormatter series3Format = new LineAndPointFormatter(Color.RED, Color.GREEN, Color.BLUE, null);

                // just for fun, add some smoothing to the lines:
                // see: http://androidplot.com/smooth-curves-and-androidplot/


                series3Format.setInterpolationParams(
                        new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

                // wrap each series in instances of ScalingXYSeries before adding to the plot
                // so that we can animate the series values below:


                final ScalingXYSeries scalingSeries3 = new ScalingXYSeries(series3, 0, ScalingXYSeries.Mode.Y_ONLY);
                plot.addSeries(scalingSeries3, series3Format);

//                plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
//                    @Override
//                    public StringBuffer format(Object obj,
//                                               @NonNull StringBuffer toAppendTo,
//                                               @NonNull FieldPosition pos) {
//                        int i = Math.round(((Number) obj).floatValue());
//                        return toAppendTo.append(domainLabels[i]);
//                    }
//
//                    @Override
//                    public Object parseObject(String source, @NonNull ParsePosition pos) {
//                        return null;
//                    }
//                });
//
//                plot.setRangeBoundaries(-1, 1, BoundaryMode.FIXED);

                // animate a scale value from a starting val of 0 to a final value of 1:
                animator3 = ValueAnimator.ofFloat(0, 1);

                // use an animation pattern that begins and ends slowly:
                animator3.setInterpolator(new AccelerateDecelerateInterpolator());

                animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float scale = valueAnimator.getAnimatedFraction();

                        scalingSeries3.setScale(scale);
                        plot.redraw();
                    }
                });
                animator3.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        // the animation is over, so show point labels:

                        series3Format.getPointLabelFormatter().getTextPaint().setColor(Color.WHITE);
                        plot.redraw();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                parentViewGroup = (ViewGroup) mView.getParent();
                if (parentViewGroup != null) {
                    parentViewGroup.removeAllViews();
                }
                mBuilder.setView(mView);
                AlertDialog dialog3 = mBuilder.create();
                dialog3.show();

                // the animation will run for 1.5 seconds:
                animator3.setDuration(1500);
                animator3.start();


                return true;

            case R.id.GyroX:
                plot.clear();

                XYSeries series4 = new SimpleXYSeries(
                        Arrays.asList(tempGyroArrayX), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Gyro X");


                LineAndPointFormatter series4Format = new LineAndPointFormatter(Color.RED, Color.GREEN, Color.BLUE, null);

                // just for fun, add some smoothing to the lines:
                // see: http://androidplot.com/smooth-curves-and-androidplot/


                series4Format.setInterpolationParams(
                        new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

                // wrap each series in instances of ScalingXYSeries before adding to the plot
                // so that we can animate the series values below:


                final ScalingXYSeries scalingSeries4 = new ScalingXYSeries(series4, 0, ScalingXYSeries.Mode.Y_ONLY);
                plot.addSeries(scalingSeries4, series4Format);

//                plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
//                    @Override
//                    public StringBuffer format(Object obj,
//                                               @NonNull StringBuffer toAppendTo,
//                                               @NonNull FieldPosition pos) {
//                        int i = Math.round(((Number) obj).floatValue());
//                        return toAppendTo.append(domainLabels[i]);
//                    }
//
//                    @Override
//                    public Object parseObject(String source, @NonNull ParsePosition pos) {
//                        return null;
//                    }
//                });
//
//                plot.setRangeBoundaries(-1, 1, BoundaryMode.FIXED);

                // animate a scale value from a starting val of 0 to a final value of 1:
                animator4 = ValueAnimator.ofFloat(0, 1);

                // use an animation pattern that begins and ends slowly:
                animator4.setInterpolator(new AccelerateDecelerateInterpolator());

                animator4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float scale = valueAnimator.getAnimatedFraction();

                        scalingSeries4.setScale(scale);
                        plot.redraw();
                    }
                });
                animator4.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        // the animation is over, so show point labels:

                        series4Format.getPointLabelFormatter().getTextPaint().setColor(Color.WHITE);
                        plot.redraw();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                parentViewGroup = (ViewGroup) mView.getParent();
                if (parentViewGroup != null) {
                    parentViewGroup.removeAllViews();
                }
                mBuilder.setView(mView);
                AlertDialog dialog4 = mBuilder.create();
                dialog4.show();

                // the animation will run for 1.5 seconds:
                animator4.setDuration(1500);
                animator4.start();


                return true;
            case R.id.GyroY:
                plot.clear();

                XYSeries series5 = new SimpleXYSeries(
                        Arrays.asList(tempGyroArrayY), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Gyro Y");


                LineAndPointFormatter series5Format = new LineAndPointFormatter(Color.RED, Color.GREEN, Color.BLUE, null);

                // just for fun, add some smoothing to the lines:
                // see: http://androidplot.com/smooth-curves-and-androidplot/


                series5Format.setInterpolationParams(
                        new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

                // wrap each series in instances of ScalingXYSeries before adding to the plot
                // so that we can animate the series values below:


                final ScalingXYSeries scalingSeries5 = new ScalingXYSeries(series5, 0, ScalingXYSeries.Mode.Y_ONLY);
                plot.addSeries(scalingSeries5, series5Format);

//                plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
//                    @Override
//                    public StringBuffer format(Object obj,
//                                               @NonNull StringBuffer toAppendTo,
//                                               @NonNull FieldPosition pos) {
//                        int i = Math.round(((Number) obj).floatValue());
//                        return toAppendTo.append(domainLabels[i]);
//                    }
//
//                    @Override
//                    public Object parseObject(String source, @NonNull ParsePosition pos) {
//                        return null;
//                    }
//                });
//
//                plot.setRangeBoundaries(-1, 1, BoundaryMode.FIXED);

                // animate a scale value from a starting val of 0 to a final value of 1:
                animator5 = ValueAnimator.ofFloat(0, 1);

                // use an animation pattern that begins and ends slowly:
                animator5.setInterpolator(new AccelerateDecelerateInterpolator());

                animator5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float scale = valueAnimator.getAnimatedFraction();

                        scalingSeries5.setScale(scale);
                        plot.redraw();
                    }
                });
                animator5.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        // the animation is over, so show point labels:

                        series5Format.getPointLabelFormatter().getTextPaint().setColor(Color.WHITE);
                        plot.redraw();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                parentViewGroup = (ViewGroup) mView.getParent();
                if (parentViewGroup != null) {
                    parentViewGroup.removeAllViews();
                }
                mBuilder.setView(mView);
                AlertDialog dialog5 = mBuilder.create();
                dialog5.show();

                // the animation will run for 1.5 seconds:
                animator5.setDuration(1500);
                animator5.start();


                return true;

            case R.id.GyroZ:
                plot.clear();

                XYSeries series6 = new SimpleXYSeries(
                        Arrays.asList(tempGyroArrayZ), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Gyro Z");


                LineAndPointFormatter series6Format = new LineAndPointFormatter(Color.RED, Color.GREEN, Color.BLUE, null);

                // just for fun, add some smoothing to the lines:
                // see: http://androidplot.com/smooth-curves-and-androidplot/


                series6Format.setInterpolationParams(
                        new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

                // wrap each series in instances of ScalingXYSeries before adding to the plot
                // so that we can animate the series values below:


                final ScalingXYSeries scalingSeries6 = new ScalingXYSeries(series6, 0, ScalingXYSeries.Mode.Y_ONLY);
                plot.addSeries(scalingSeries6, series6Format);

//                plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
//                    @Override
//                    public StringBuffer format(Object obj,
//                                               @NonNull StringBuffer toAppendTo,
//                                               @NonNull FieldPosition pos) {
//                        int i = Math.round(((Number) obj).floatValue());
//                        return toAppendTo.append(domainLabels[i]);
//                    }
//
//                    @Override
//                    public Object parseObject(String source, @NonNull ParsePosition pos) {
//                        return null;
//                    }
//                });
//
//                plot.setRangeBoundaries(-1, 1, BoundaryMode.FIXED);

                // animate a scale value from a starting val of 0 to a final value of 1:
                animator6 = ValueAnimator.ofFloat(0, 1);

                // use an animation pattern that begins and ends slowly:
                animator6.setInterpolator(new AccelerateDecelerateInterpolator());

                animator6.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float scale = valueAnimator.getAnimatedFraction();

                        scalingSeries6.setScale(scale);
                        plot.redraw();
                    }
                });
                animator6.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        // the animation is over, so show point labels:

                        series6Format.getPointLabelFormatter().getTextPaint().setColor(Color.WHITE);
                        plot.redraw();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                parentViewGroup = (ViewGroup) mView.getParent();
                if (parentViewGroup != null) {
                    parentViewGroup.removeAllViews();
                }
                mBuilder.setView(mView);
                AlertDialog dialog6 = mBuilder.create();
                dialog6.show();

                // the animation will run for 1.5 seconds:
                animator6.setDuration(1500);
                animator6.start();


                return true;

            case R.id.TempMenuID:
                plot.clear();

                XYSeries series7 = new SimpleXYSeries(
                        Arrays.asList(tempTempArray), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Temp");


                LineAndPointFormatter series7Format = new LineAndPointFormatter(Color.RED, Color.GREEN, Color.BLUE, null);

                // just for fun, add some smoothing to the lines:
                // see: http://androidplot.com/smooth-curves-and-androidplot/


                series7Format.setInterpolationParams(
                        new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

                // wrap each series in instances of ScalingXYSeries before adding to the plot
                // so that we can animate the series values below:


                final ScalingXYSeries scalingSeries7 = new ScalingXYSeries(series7, 0, ScalingXYSeries.Mode.Y_ONLY);
                plot.addSeries(scalingSeries7, series7Format);

//                plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
//                    @Override
//                    public StringBuffer format(Object obj,
//                                               @NonNull StringBuffer toAppendTo,
//                                               @NonNull FieldPosition pos) {
//                        int i = Math.round(((Number) obj).floatValue());
//                        return toAppendTo.append(domainLabels[i]);
//                    }
//
//                    @Override
//                    public Object parseObject(String source, @NonNull ParsePosition pos) {
//                        return null;
//                    }
//                });
//
//                plot.setRangeBoundaries(-1, 1, BoundaryMode.FIXED);

                // animate a scale value from a starting val of 0 to a final value of 1:
                animator7 = ValueAnimator.ofFloat(0, 1);

                // use an animation pattern that begins and ends slowly:
                animator7.setInterpolator(new AccelerateDecelerateInterpolator());

                animator7.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float scale = valueAnimator.getAnimatedFraction();

                        scalingSeries7.setScale(scale);
                        plot.redraw();
                    }
                });
                animator7.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        // the animation is over, so show point labels:

                        series7Format.getPointLabelFormatter().getTextPaint().setColor(Color.WHITE);
                        plot.redraw();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                parentViewGroup = (ViewGroup) mView.getParent();
                if (parentViewGroup != null) {
                    parentViewGroup.removeAllViews();
                }
                mBuilder.setView(mView);
                AlertDialog dialog7 = mBuilder.create();
                dialog7.show();

                // the animation will run for 1.5 seconds:
                animator7.setDuration(1500);
                animator7.start();


                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_run_data);

//
        mBuilder = new AlertDialog.Builder(ViewAllRunData.this);
        mView = getLayoutInflater().inflate(R.layout.inflatedxy_plot, null);

        plot = (XYPlot) mView.findViewById(R.id.plotInflated);

//

        myDb = new DataBaseHelper(this);
        lv = (ListView) findViewById(R.id.listView);


        List<String> all = myDb.getAllCategory();
        if (all.size() > 0) // check if list contains items.
        {

            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, all);
            lv.setAdapter(arrayAdapter);
        } else {
            Toast.makeText(this, "No items to display", Toast.LENGTH_LONG).show();
        }
        lv.setLongClickable(true);
        this.registerForContextMenu(lv);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {


                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    // show message
                    showMessage("Error", "Nothing found");
//                    return;
                }
                ArrayList<ArrayList> accelDoubleListGet = new ArrayList<>();
                ArrayList<ArrayList> gyroDoubleListGet = new ArrayList<>();
                ArrayList<ArrayList> tempDoubleListGet = new ArrayList<>();

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    if (res.getString(6).trim().equalsIgnoreCase(all.get(i).toString().trim())) {
//                        buffer.append("Accelerometer Data :" + res.getString(1) + "\n");
//                        buffer.append("Magnitude of Acceleration :" + res.getString(2) + "\n");
//                        buffer.append("Gyroscope Data :" + res.getString(3) + "\n");
//                        buffer.append("Temperature Data :" + res.getString(4) + "\n");
//                        buffer.append("Speed Data : " + res.getString(5) + "\n");
//                        buffer.append("Date : " + res.getString(6) + "\n");
//                        buffer.append("Res is : " + res.getCount() + "\n\n");
//                        buffer.append("I is: " + i + "\n\n");
//                        buffer.append("ID is: " + res.getString(0) + "\n\n");
                        Gson gson = new Gson();
                        accelDoubleListGet = gson.fromJson(res.getString(1), ArrayList.class);
                gyroDoubleListGet = gson.fromJson(res.getString(3), ArrayList.class);
                tempDoubleListGet = gson.fromJson(res.getString(4), ArrayList.class);

                        Toast.makeText(ViewAllRunData.this, "match Found", Toast.LENGTH_SHORT).show();

                    }

                }
//                accelDoubleListGet
//                First Get Is The Accel Set Poistion
//                Second Get Is X,Y,Z Value


                ArrayList<Double> tempAccelListX = new ArrayList<>();
                for (int j = 0; j < accelDoubleListGet.size(); j++) {
//                            Get Every Aceel X
                    tempAccelListX.add((Double) accelDoubleListGet.get(j).get(0));
                }
//                Remove due to 0s on start up
                tempAccelListX.remove(0);
                tempAccelArrayX = tempAccelListX.toArray(new Double[tempAccelListX.size()]);


                ArrayList<Double> tempAccelListY = new ArrayList<>();
                for (int j = 0; j < accelDoubleListGet.size(); j++) {
//                            Get Every Aceel Y
                    tempAccelListY.add((Double) accelDoubleListGet.get(j).get(1));
                }

//                Remove due to 0s on start up
                tempAccelListY.remove(0);

                tempAccelArrayY = tempAccelListY.toArray(new Double[tempAccelListY.size()]);

                ArrayList<Double> tempAccelListZ = new ArrayList<>();
                for (int j = 0; j < accelDoubleListGet.size(); j++) {
//                            Get Every Aceel Z
                    tempAccelListZ.add((Double) accelDoubleListGet.get(j).get(2));
                }

//                Remove due to 0s on start up
                tempAccelListZ.remove(0);

                tempAccelArrayZ = tempAccelListZ.toArray(new Double[tempAccelListZ.size()]);

                ArrayList<Double> tempGyroListX = new ArrayList<>();
                for (int j = 0; j < gyroDoubleListGet.size(); j++) {
//                            Get Every Aceel Z
                    tempGyroListX.add((Double) gyroDoubleListGet.get(j).get(0));
                }

//                Remove due to 0s on start up
                tempGyroListX.remove(0);

                tempGyroArrayX = tempGyroListX.toArray(new Double[tempGyroListX.size()]);

                ArrayList<Double> tempGyroListY = new ArrayList<>();
                for (int j = 0; j < gyroDoubleListGet.size(); j++) {
//                            Get Every Aceel Z
                    tempGyroListY.add((Double) gyroDoubleListGet.get(j).get(1));
                }
//                Remove due to 0s on start up
                tempGyroListY.remove(0);
                tempGyroArrayY = tempGyroListY.toArray(new Double[tempGyroListY.size()]);

                ArrayList<Double> tempGyroListZ = new ArrayList<>();
                for (int j = 0; j < gyroDoubleListGet.size(); j++) {
//                            Get Every Aceel Z
                    tempGyroListZ.add((Double) gyroDoubleListGet.get(j).get(2));
                }

//                Remove due to 0s on start up
                tempGyroListZ.remove(0);
                tempGyroArrayZ = tempGyroListZ.toArray(new Double[tempGyroListZ.size()]);






//                Remove due to 0s on start up
                tempDoubleListGet.remove(0);
                tempTempArray = tempDoubleListGet.toArray(new Double[tempDoubleListGet.size()]);
//
//                XYSeries series1 = new SimpleXYSeries(
//                        Arrays.asList(tempAccelArrayX), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Accel X");
//
//
//                XYSeries series2 = new SimpleXYSeries(
//                        Arrays.asList(tempAccelArrayY), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Accel Y");
//
//
//                XYSeries series3 = new SimpleXYSeries(
//                        Arrays.asList(tempAccelArrayZ), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Accel Z");
//
//
//                // create formatters to use for drawing a series using LineAndPointRenderer
//                // and configure them from xml:
//                LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.RED, Color.GREEN, Color.BLUE, null);
//
//                LineAndPointFormatter series2Format = new LineAndPointFormatter(Color.RED, Color.GREEN, Color.BLUE, null);
//
//
//                LineAndPointFormatter series3Format = new LineAndPointFormatter(Color.RED, Color.GREEN, Color.BLUE, null);
//
//                // just for fun, add some smoothing to the lines:
//                // see: http://androidplot.com/smooth-curves-and-androidplot/
//                series1Format.setInterpolationParams(
//                        new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));
//
//                series2Format.setInterpolationParams(
//                        new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));
//
//                series3Format.setInterpolationParams(
//                        new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));
//
//                // wrap each series in instances of ScalingXYSeries before adding to the plot
//                // so that we can animate the series values below:
//                final ScalingXYSeries scalingSeries1 = new ScalingXYSeries(series1, 0, ScalingXYSeries.Mode.Y_ONLY);
//                plot.addSeries(scalingSeries1, series1Format);
//
//                final ScalingXYSeries scalingSeries2 = new ScalingXYSeries(series2, 0, ScalingXYSeries.Mode.Y_ONLY);
//                plot.addSeries(scalingSeries2, series2Format);
//
//                final ScalingXYSeries scalingSeries3 = new ScalingXYSeries(series3, 0, ScalingXYSeries.Mode.Y_ONLY);
//                plot.addSeries(scalingSeries3, series3Format);
//
//                plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
//                    @Override
//                    public StringBuffer format(Object obj,
//                                               @NonNull StringBuffer toAppendTo,
//                                               @NonNull FieldPosition pos) {
//                        int i = Math.round(((Number) obj).floatValue());
//                        return toAppendTo.append(domainLabels[i]);
//                    }
//
//                    @Override
//                    public Object parseObject(String source, @NonNull ParsePosition pos) {
//                        return null;
//                    }
//                });
//
//                plot.setRangeBoundaries(-1, 1, BoundaryMode.FIXED);
//
//                // animate a scale value from a starting val of 0 to a final value of 1:
//                ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
//
//                // use an animation pattern that begins and ends slowly:
//                animator.setInterpolator(new AccelerateDecelerateInterpolator());
//
//                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                        float scale = valueAnimator.getAnimatedFraction();
//                        scalingSeries1.setScale(scale);
//                                scalingSeries2.setScale(scale);
//                                scalingSeries3.setScale(scale);
//                        plot.redraw();
//                    }
//                });
//                animator.addListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animator) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animator) {
//                        // the animation is over, so show point labels:
//                        series1Format.getPointLabelFormatter().getTextPaint().setColor(Color.WHITE);
//                        series2Format.getPointLabelFormatter().getTextPaint().setColor(Color.WHITE);
//                        series3Format.getPointLabelFormatter().getTextPaint().setColor(Color.WHITE);
//                        plot.redraw();
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animator) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animator) {
//
//                    }
//                });
//
//                mBuilder.setView(mView);
//                AlertDialog dialog = mBuilder.create();
//                dialog.show();
//
//                // the animation will run for 1.5 seconds:
//                animator.setDuration(1500);
//                animator.start();
//
//


                return false;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    // show message
                    showMessage("Error", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    if (res.getString(6).trim().equalsIgnoreCase(all.get(i).toString().trim())) {
                        buffer.append("Accelerometer Data :" + res.getString(1) + "\n");
                        buffer.append("Magnitude of Acceleration :" + res.getString(2) + "\n");
                        buffer.append("Gyroscope Data :" + res.getString(3) + "\n");
                        buffer.append("Temperature Data :" + res.getString(4) + "\n");
                        buffer.append("Speed Data : " + res.getString(5) + "\n");
                        buffer.append("Date : " + res.getString(6) + "\n");
                        buffer.append("Res is : " + res.getCount() + "\n\n");
                        buffer.append("I is: " + i + "\n\n");
                        buffer.append("ID is: " + res.getString(0) + "\n\n");


                    }

//                    showMessage("Data","Res: " + res.getCount() + "\n i: " + i);
                }

                // Show all data
                showMessage("Data", buffer.toString());
            }
        });


    }


    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}
