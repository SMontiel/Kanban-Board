package com.smontiel.kanbanboard.main_view;

import com.smontiel.kanbanboard.BasePresenter;
import com.smontiel.kanbanboard.BaseView;

import java.util.List;

/**
 * Created by Salvador Montiel on 14/11/17.
 */

public class CardsContract {
    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showTasks(List<TaskItem> tasks);

        /*void showLoadingColumnsError();

        void showNoColumns();*/

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void loadTasks(int idColumn);
    }
}
