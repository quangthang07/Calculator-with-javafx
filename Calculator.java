
/*計算のアプリケーション
 * ユーザは用意されたボタンを使い、計算したい式を入力して、最後＝ボタンを押したら結果を返す。
 * 使用したGUIは BorderPane, GridPane, FlowPane, Label, Text
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.*;

public class Calculator extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage myStage) {
		
		myStage.setTitle("Personal Calculator");
		
		GridPane gridPane = new GridPane();
		BorderPane borderPane = new BorderPane();
		FlowPane flowPane = new FlowPane();
		
		Scene myScene = new Scene(borderPane, 250,300);
		myStage.setScene(myScene);
		
		TextField text = new TextField();
		text.setPrefSize(250, 90);
		//borderPane.setTop(text);
		
		Label previousOp = new Label("Previous Operation");
		
		TextField preOpField = new TextField();
		preOpField.setPrefSize(250, 20);
		
		flowPane.getChildren().addAll(text, previousOp, preOpField);
		borderPane.setTop(flowPane);
		borderPane.setCenter(gridPane);
		BorderPane.setAlignment(flowPane, Pos.CENTER);
		BorderPane.setAlignment(gridPane, Pos.CENTER);
		
		// 入力したボタンのリストを作る。	
		ArrayList<String> historyList = new ArrayList<String>();
		
		// button setup
		Button button1 = new Button("1");
		button1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent number1) {
				text.appendText("1");
				historyList.add("1");
			}
		});
		Button button2 = new Button("2");
		button2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent number2) {
				text.appendText("2");
				historyList.add("2");
			}
		});
		Button button3 = new Button("3");
		button3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent number3) {
				text.appendText("3");
				historyList.add("3");
			}
		});
		Button button4 = new Button("4");
		button4.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent number4) {
				text.appendText("4");
				historyList.add("4");
			}
		});
		Button button5 = new Button("5");
		button5.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent number5) {
				text.appendText("5");
				historyList.add("5");
			}
		});
		Button button6 = new Button("6");
		button6.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent number6) {
				text.appendText("6");
				historyList.add("6");
			}
		});
		Button button7 = new Button("7");
		button7.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent number7) {
				text.appendText("7");
				historyList.add("7");
			}
		});
		Button button8 = new Button("8");
		button8.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent number8) {
				text.appendText("8");
				historyList.add("8");
			}
		});
		Button button9 = new Button("9");
		button9.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent number9) {
				text.appendText("9");
				historyList.add("9");
			}
		});
		Button button0 = new Button("0");
		button0.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent number0) {
				text.appendText("0");
				historyList.add("0");
			}
		});
		Button buttonDot = new Button(".");
		buttonDot.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent buttonDot) {
				text.appendText(".");
				historyList.add(".");
			}
		});
		Button openBracket = new Button("(");
		openBracket.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent buttonDot) {
				text.appendText("( ");
				historyList.add("( ");
			}
		});
		Button closeBracket = new Button(")");
		closeBracket.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent buttonDot) {
				text.appendText(" )");
				historyList.add(" )");
			}
		});
		Button del = new Button("Del");
		del.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent del) {
				if (!historyList.isEmpty()) {
					historyList.remove(historyList.size()-1);
					text.clear();
					for (String str : historyList) {
						text.appendText(str);
					}
				}
			}
		});
		Button buttonAdd = new Button("+");
		buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent buttonAdd) {
				text.appendText(" + ");
				historyList.add(" + ");
			}
		});
		Button buttonSub = new Button("-");
		buttonSub.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent buttonSub) {
				text.appendText(" - ");
				historyList.add(" - ");
			}
		});
		Button buttonMulti = new Button("*");
		buttonMulti.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent buttonMulti) {
				text.appendText(" * ");
				historyList.add(" * ");
			}
		});
		Button buttonDiv = new Button("/");
		buttonDiv.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent buttonDiv) {
				text.appendText(" / ");
				historyList.add(" / ");
			}
		});
		Button equal = new Button("=");
		// ＝を押したとき、計算処理を実行
		equal.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent equal) {
				if (!text.getText().equals("")) {
					String s = new String(text.getText());
					
					String[] arr = s.split(" ");
					// convert an arr to list
					List<String> list = new ArrayList<String>(Arrays.asList(arr));
					
					String errorAnounce = "正しい式をもう一度入力してください！";
					try {
						bracketCompute(list);
						operate(list);
						toInteger(list);
						text.setText(list.get(0));
					}catch (NumberFormatException e){
						text.setText(errorAnounce);
					}
					preOpField.setText(s);
					preOpField.appendText(" = " + list.get(0));
					
					historyList.clear();
					historyList.add(list.get(0));
				}
			}
		});
		Button clear = new Button("C");
		clear.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent clear) {
				text.clear();
				historyList.clear();
			}
		});
		// Buttonの位置を配置
		gridPane.setVgap(4);
		gridPane.setHgap(5);
		gridPane.setGridLinesVisible(false);
		
		gridPane.add(button1, 0, 0);
		gridPane.add(button4, 0, 1);
		gridPane.add(button7, 0, 2);
		gridPane.add(button0, 0, 3);
		
		gridPane.add(button2, 1, 0);
		gridPane.add(button5, 1, 1);
		gridPane.add(button8, 1, 2);
		gridPane.add(buttonDot, 1, 3);
		
		
		gridPane.add(button3, 2, 0);
		gridPane.add(button6, 2, 1);
		gridPane.add(button9, 2, 2);
		gridPane.add(equal, 2, 3);
		
		gridPane.add(buttonAdd, 3, 0);
		gridPane.add(buttonSub, 3, 1);
		gridPane.add(openBracket, 3, 2);
		gridPane.add(clear, 3, 3);
		
		gridPane.add(buttonMulti, 4, 0);
		gridPane.add(buttonDiv, 4, 1);
		gridPane.add(closeBracket, 4, 2);
		gridPane.add(del, 4, 3);
		
		gridPane.setStyle("-fx-alignment: center;");
		myStage.show();
	}
	// 計算を実行する再帰メソッド
	void operate(List<String> list) {
		// 終了条件
		if (list.size() <= 1) {
			return;
		}
		// 入力による先頭のマイナスのナンバーを負の数に変換（例： - 2 -> -2）
		if (list.get(0).isEmpty()) {
			if (list.get(1).equals("-")) {
				double minusNumber = Double.parseDouble(list.get(2));
				minusNumber = 0-minusNumber;
				list.set(2, String.valueOf(minusNumber));
				list.remove(0);
				list.remove(0);
			}
		}
		
		// 掛け算と除算の処理
		if (list.contains("*") || list.contains("/")) {
			int multiPos = 100;
			int divPos = 100;
			if (list.contains("*")) multiPos = list.indexOf("*");
			if (list.contains("/")) divPos = list.indexOf("/");
			if (multiPos < divPos) {
				double firstNum = Double.parseDouble(list.get(multiPos-1));
				double secondNum = Double.parseDouble(list.get(multiPos+1));
				double equal = firstNum*secondNum;
				list.set(multiPos, String.valueOf(equal));
				list.remove(multiPos-1);
				list.remove(multiPos);
			}else {
				double firstNum = Double.parseDouble(list.get(divPos-1));
				double secondNum = Double.parseDouble(list.get(divPos+1));
				double equal = firstNum/secondNum;
				list.set(divPos, String.valueOf(equal));
				list.remove(divPos-1);
				list.remove(divPos);
			}
		}else if (list.contains("+") || list.contains("-")) {	//足し算と引き算の処理
			int addPos = 100;
			int subPos = 100;
			if (list.contains("+")) addPos = list.indexOf("+");
			if (list.contains("-")) subPos = list.indexOf("-");
			if (addPos < subPos) {
				double firstNum = Double.parseDouble(list.get(addPos-1));
				double secondNum = Double.parseDouble(list.get(addPos+1));
				double equal = firstNum+secondNum;
				list.set(addPos, String.valueOf(equal));
				list.remove(addPos-1);
				list.remove(addPos);
			}else {
				double firstNum = Double.parseDouble(list.get(subPos-1));
				double secondNum = Double.parseDouble(list.get(subPos+1));
				double equal = firstNum-secondNum;
				list.set(subPos, String.valueOf(equal));
				list.remove(subPos-1);
				list.remove(subPos);
			}
		}
		
		operate(list);
	}
	// カッコ内の式を計算するメソッド
	void bracketCompute(List<String> list) {
		if (list.contains("(") && list.contains(")")) {
			
			int openBracPos = list.indexOf("(");
			int closeBracPos = list.indexOf(")");
			
			for(int i = 0; i < list.size(); i++) {
				if (list.get(i).equals("(")) {
					openBracPos = i;
				}
				if (list.get(i).equals(")")) {
					closeBracPos = i;
					break;
				}
			}
			
			
			//メインリストから（）の部分を取り出し、subLsに入れる。
			List<String> subLs = new ArrayList<String>();
			for(int i = openBracPos; i <= closeBracPos; i++) {
				subLs.add(list.get(i));
			}
			for(int i = closeBracPos; i >= openBracPos; i--) {
				list.remove(i);
			}
			subLs.remove(closeBracPos - openBracPos);
			subLs.remove(0);
			
			operate(subLs);
			list.add(openBracPos, subLs.get(0)); 	//（）内で計算された結果はメインリストに戻す。
			bracketCompute(list);
		}
	}
	// 結果は整数であれば、intに変換する（例：2.0 -> 2)、実数はそのまま
	void toInteger(List<String> list) {
		
		double doubNum = Double.parseDouble(list.get(0));
		if ((int)doubNum == doubNum)  list.set(0, String.valueOf((int)doubNum));
		else list.set(0, String.valueOf(doubNum));
	}
}
