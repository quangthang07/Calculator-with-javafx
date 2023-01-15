
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;



public class BlockObjectBuilding extends Application {
	//width and height of window
	double width = 800;
	double height = 500;
	
	double anchorX, anchorY;
	double anchorAngleX, anchorAngleY;
	DoubleProperty angleX = new SimpleDoubleProperty();
	DoubleProperty angleY = new SimpleDoubleProperty();
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage myStage) {
		 myStage.setTitle("3D Block");
		 Box box = new Box(100, 20, 50);
		 
		 SmartGroup group = new SmartGroup();
		 group.getChildren().add(box);
		 group.getChildren().addAll(preparedLightSource());
		 
		 Camera camera  = new PerspectiveCamera(); // fixEyeAtCameraZero: true
//		 camera.setNearClip(-100);
//		 camera.setFarClip(500);
//		 camera.translateZProperty().set(-100);
		 
		 Scene scene = new Scene(group, width, height);	// depthBuffed: true
		 scene.setFill(Color.BEIGE);
		 scene.setCamera(camera);
		 
		 group.translateXProperty().set(width/2);
		 group.translateYProperty().set(height/2);
		 group.translateZProperty().set(-500);
		 
		 //Transform transform = new Rotate(65, Rotate.Y_AXIS);
		 //box.getTransforms().add(transform);
		 
		 initMouseControl(group, scene, myStage);
		
		 myStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			switch(event.getCode()) {
			case W: 
				group.translateZProperty().set(group.getTranslateZ() + 100);
				break;
			case S:
				group.translateZProperty().set(group.getTranslateZ() -100);
				break;
			case A:
				group.rotateByX(10);
				break;
			case D:
				group.rotateByX(-10);
				break;
			case Q:
				group.rotateByY(10);
				break;
			case E:
				group.rotateByY(-10);
			}
		 });
		 
		 myStage.setScene(scene);		 
		 myStage.show();
		 
		 AnimationTimer timer = new AnimationTimer() {
			 public void handle(long now) {
				 pointlight.setRotate(pointlight.getRotate()+1);
			 }
		 };
		 timer.start();
	}
	
	void initMouseControl(SmartGroup group, Scene scene, Stage stage) {
		Rotate xRotate;
		Rotate yRotate;
		group.getTransforms().addAll(
				xRotate = new Rotate(0, Rotate.X_AXIS),
				yRotate = new Rotate(0, Rotate.Y_AXIS)
				);
		xRotate.angleProperty().bind(angleX);
		yRotate.angleProperty().bind(angleY);
		
		scene.setOnMousePressed(event -> {
			anchorX = event.getSceneX();
			anchorY = event.getSceneY();
			anchorAngleX = angleX.get();
			anchorAngleY = angleY.get();
		});
	
		scene.setOnMouseDragged(event -> {
			angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
			angleY.set(anchorAngleY + anchorX - event.getSceneX());
		});
		
		stage.addEventHandler(ScrollEvent.SCROLL, event -> {
			double delta = event.getDeltaY();
			group.translateZProperty().set(group.getTranslateZ() + delta);
		});
	}
	
	private final PointLight pointlight = new PointLight();
	
	Node[] preparedLightSource() {
		// pointlight
		pointlight.setColor(Color.WHITE);
		pointlight.getTransforms().add(new Translate(0, -50, 100));
		pointlight.setRotationAxis(Rotate.X_AXIS);
		
		Sphere point = new Sphere(2);
		point.getTransforms().setAll(pointlight.getTransforms());
		point.rotateProperty().bind(pointlight.rotateProperty());
		point.rotationAxisProperty().bind(pointlight.rotationAxisProperty());
		
		return new Node[] {pointlight, point};
	}
	
	class SmartGroup extends Group {
		Rotate r;
		Transform t = new Rotate();
		
		void rotateByX(double ang) {
			r = new Rotate(ang, Rotate.X_AXIS);
			t = t.createConcatenation(r);
			this.getTransforms().clear();
			this.getTransforms().addAll(t);
			
		}
		void rotateByY(double ang) {
			r = new Rotate(ang, Rotate.Y_AXIS);
			t = t.createConcatenation(r);
			this.getTransforms().clear();
			this.getTransforms().addAll(t);
		}
	}
}
