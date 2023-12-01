package com.example.stacker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class BlockView extends View {
    private static final int BLOCK_SIZE = 200;
    private static final int NUM_BLOCKS = 3;
    private static final int BLOCK_GAP = 10;
    private static final int DELAY_MILLIS = 100;
    private static final int MAX_BLOCKS_ON_SCREEN = 10;

    private Paint paint;
    private List<int[]> blockPositionsList;
    private int currentBlockDirection = 1;
    private Handler handler;
    private int currentActiveBlockIndex = 0;

    public BlockView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        handler = new Handler(Looper.getMainLooper());

        blockPositionsList = new ArrayList<>();
        addNewBlock();  // Add the first block
    }

    private void addNewBlock() {
        if (blockPositionsList.size() >= MAX_BLOCKS_ON_SCREEN) {
            // Game over, reached the top
            return;
        }

        int[] blockPositions = new int[NUM_BLOCKS];
        for (int i = 0; i < NUM_BLOCKS; i++) {
            blockPositions[i] = i * (BLOCK_SIZE + BLOCK_GAP);
        }
        blockPositionsList.add(blockPositions);
        currentActiveBlockIndex = blockPositionsList.size() - 1;
        moveCurrentBlock();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            addNewBlock();
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int blockIdx = 0; blockIdx < blockPositionsList.size(); blockIdx++) {
            int[] blockPositions = blockPositionsList.get(blockIdx);
            for (int pos : blockPositions) {
                canvas.drawRect(
                        pos,
                        getHeight() - (blockIdx + 1) * BLOCK_SIZE - blockIdx * BLOCK_GAP,
                        pos + BLOCK_SIZE,
                        getHeight() - blockIdx * (BLOCK_SIZE + BLOCK_GAP),
                        paint
                );
            }
        }

        invalidate();
    }

    private void moveCurrentBlock() {
        if (currentActiveBlockIndex >= blockPositionsList.size()) {
            return;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int[] currentBlockPositions = blockPositionsList.get(currentActiveBlockIndex);
                for (int i = 0; i < NUM_BLOCKS; i++) {
                    currentBlockPositions[i] += currentBlockDirection * 10;

                    if (currentBlockPositions[i] > getWidth() - BLOCK_SIZE || currentBlockPositions[i] < 0) {
                        currentBlockDirection = -currentBlockDirection;
                        break;
                    }
                }
                invalidate();
                moveCurrentBlock();
            }
        }, DELAY_MILLIS);
    }
}
