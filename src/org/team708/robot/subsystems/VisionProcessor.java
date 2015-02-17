//package org.team708.robot.subsystems;
//
//import org.team708.robot.commands.visionProcessor.ProcessData;
//
//import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.networktables.NetworkTable;
//import edu.wpi.first.wpilibj.networktables2.type.NumberArray;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;
//
///**
// *
// */
//public class VisionProcessor extends Subsystem {
//    
//	private NetworkTable roboRealmInfo;
//	private NumberArray toteBoundaries;
//	private NumberArray containerCrosshair;
//	private boolean hasTote;
//	public boolean hasContainer;
//	
//	private final double imageWidth = 320;
//	private double centerX;
//	
//	private double thresholdX = 20.0;
//	private double thresholdY = 0.1;
//	
////	private double containerX;
//	private double[] toteX = new double[2];
//	private double toteWidth;
//	
//	public VisionProcessor() {
//		super("Vision Processor");
//		roboRealmInfo = NetworkTable.getTable("vision");
//		toteBoundaries = new NumberArray();
//		containerCrosshair = new NumberArray();
//		centerX = imageWidth / 2;
//	}
//	
//	public void processData() {
//		try {
////			roboRealmInfo.retrieveValue("CROSSHAIR_COORDINATES", containerCrosshair);
////			if (containerCrosshair.size() > 0) {
////				containerX = containerCrosshair.get(0);
////				hasContainer = true;
////			} else {
////				hasContainer = false;
////			}
//			
//			roboRealmInfo.retrieveValue("MEQ_COORDINATES", toteBoundaries);
//			if (toteBoundaries.size() > 0) {
//				toteX[0] = toteBoundaries.get(0);
//				toteX[1] = toteBoundaries.get(2);
//				hasTote = true;
//			} else {
//				hasTote = false;
//			}
//			toteWidth = toteX[0] - toteX[1];
//		} catch (TableKeyNotDefinedException e) {
////			e.printStackTrace();
//		}
//	}
//	
//	public double getRotate() {
//		double rotate;
//		
//		if (hasTote) {
//			double difference = centerX - ((toteX[0] + toteX[1]) / 2);
//			
//			if (Math.abs(difference) <= thresholdX) {
//				difference = 0.0;
//			}
//			
//			rotate = difference / centerX;
//			
//			if (Math.abs(rotate) < 0.65 && Math.abs(rotate) != 0.0) {
//				if (rotate >= 0.0) {
//					rotate = 0.65;
//				} else {
//					rotate = -0.65;
//				}
//			}
//		} else {
//			rotate = 0.65;
//		}
//		
//		return rotate;
//	}
//	
//	public double getMove(double targetAmount) {
//		double move;
//		
//		if (hasTote) {
//			double ratio = toteWidth / imageWidth;
//			
//			double difference = ratio - targetAmount;
//			
//			if (Math.abs(difference) <= thresholdY) {
//				difference = 0.0;
//			}
//			
//			move = difference / targetAmount;
//			
//			if (Math.abs(move) < 0.65 && Math.abs(move) != 0.0) {
//				if (move >= 0.0) {
//					move = 0.65;
//				} else {
//					move = -0.65;
//				}
//			}	
//		} else {
//			move = 0.0;
//		}
//		
//		return move;
//	}
//	
//	/**
//	 * Returns if the robot sees a container
//	 * @return
//	 */
//	public boolean isHasContainer() {
//		return hasContainer;
//	}
//	
//	/**
//	 * Returns if the robot sees a tote
//	 * @return
//	 */
//	public boolean isHasTote() {
//		return hasTote;
//	}
//	
//	public void sendToDashboard() {
//		SmartDashboard.putBoolean("Has Tote", isHasTote());
//		SmartDashboard.putBoolean("Has Container", isHasContainer());
//		SmartDashboard.putNumber("Target Width", toteWidth);
//		SmartDashboard.putNumber("Crosshair Size", containerCrosshair.size());
//		SmartDashboard.putNumber("Tote Coordinates", toteBoundaries.size());
//	}
//
//    public void initDefaultCommand() {
//        // Set the default command for a subsystem here.
//        setDefaultCommand(new ProcessData());
//    }
//}
//
