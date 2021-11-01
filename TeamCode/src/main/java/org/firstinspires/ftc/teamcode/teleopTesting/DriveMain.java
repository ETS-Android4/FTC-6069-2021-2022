/* Copyright (c) 2021 FIRST. All rights reserved.
*
* Redistribution and use in source and binary forms, with or without modification,
* are permitted (subject to the limitations in the disclaimer below) provided that
* the following conditions are met:
*
* Redistributions of source code must retain the above copyright notice, this list
* of conditions and the following disclaimer.
*
* Redistributions in binary form must reproduce the above copyright notice, this
* list of conditions and the following disclaimer in the documentation and/or
* other materials provided with the distribution.
*
* Neither the name of FIRST nor the names of its contributors may be used to endorse or
* promote products derived from this software without specific prior written permission.
*
* NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
* LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
* "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
* THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
* ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
* FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
* DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
* SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
* CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
* OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
* OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.lang.*;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

/*
	Upload to robot:
	1. connect to robot wifi (6069 RC)
	2. Tools > External Tools > ADB Connect
		- make sure device says REV Robotics control hub v1.0 or something
	3. Green play button (run button)

	Open robot output logs (android studio):
	1. hit little box square in verryyy bottom left corner
	2. hit run

	Error fixes:
		- ERROR: "Motor must be set to a target position before setting to RUN_TO_POSITION"
		- FIX:
			1. restart robot, restart app
			2. make sure all motors have a run mode set
*/

/*
	Issues:
	- intake power too low
	- movement are inverted
	- right trigger not working
	- servos not working

	Wheel issues:
	- front left wheel spinning slowly
	- other wheel spinning weirdly
*/


@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class DriveMain extends OpMode
{
	// Declare OpMode members.

	//private ElapsedTime runtime;
	private MecanumDrive mecanumDrive;
	private int offsetAngle;
	private int direc; // variable to determine which direction to move the robot, 1 to go forward and -1 for backwards
	private boolean[] previousButtonStates;


	/*
	* Code to run ONCE when the driver hits INIT
	*/
	@Override
	public void init() {
		telemetry.addData("Status", "Initializing");
		mecanumDrive = new MecanumDrive(hardwareMap);
		// class initializations go here
		offsetAngle = 0;
		direc = 1;
		previousButtonStates = updateButtonList();
		// ex: wobbleMech = new WobbleMech(hardwareMap);		


		// Initialize the hardware variables. Note that the strings used here as parameters
		// to 'get' must correspond to the names assigned during the robot configuration
		// step (using the FTC Robot Controller app on the phone).


		// Most robots need the motor on one side to be reversed to drive forward
		// Reverse the motor that runs backwards when connected directly to the battery

		// Tell the driver that initialization is complete.
		telemetry.addData("Status", "Initialized");
	}

	/*
	* Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
	*/
	@Override
	public void init_loop() {
	}

	/*
	* Code to run ONCE when the driver hits PLAY
	*/
	@Override
	public void start() {
		//runtime.reset();
	}


	/*
	* Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
	*/
	@Override
	public void loop() {

		boolean[] currentButtonStates = updateButtonList();
		// Setup a variable for each drive wheel to save power level for telemetry

		// Show the elapsed game time and wheel power.
		double speed = 0.2; // adjust accordingly
		//if(gamepad1.right_trigger > 0.5){
		//    speed += (1-speed)*(2*(gamepad1.right_trigger - 0.5));
		//}

		double magnitude = Math.hypot(-gamepad1.left_stick_x, gamepad1.left_stick_y);
		double robotAngle = Math.atan2(gamepad1.left_stick_y, -gamepad1.left_stick_x) - Math.PI / 4;
		telemetry.addData("robot angle", robotAngle);
		robotAngle += offsetAngle / 180.0 * Math.PI;
		double rightX = -gamepad1.right_stick_x;
		mecanumDrive.polarMove(robotAngle, rightX, 0.5 * direc * speed * magnitude);
		telemetry.update();


		if (previousButtonStates[0] != currentButtonStates[0]) { // functions only when the button is held
																// ex: move linear slide up while button is held
			if (currentButtonStates[0]) {
			} else {
			}
		} if (previousButtonStates[1] != currentButtonStates[1]) {
			if (currentButtonStates[1]) {
			} else {
			}
		}
		if (previousButtonStates[2] != currentButtonStates[2]) { // toggles the function/single sided function 
																// ex: turning flywheel on/off or turning intake on
			if (currentButtonStates[2]) {
			}
		} if(previousButtonStates[3] != currentButtonStates[3]) {
			if (currentButtonStates[3]) {
			}
		} if (previousButtonStates[4] != currentButtonStates[4]) {
			if (currentButtonStates[4]){
			} else {
			}
		} if (previousButtonStates[5] != currentButtonStates[5]) {
			if (currentButtonStates[5]) {
			} else {
			}
		} if (previousButtonStates[6] != currentButtonStates[6]) { 
			if (currentButtonStates[6]){
			}
		} if (previousButtonStates[7] != currentButtonStates[7]) {
			if (currentButtonStates[7]) {
			}
		} if (previousButtonStates[8] != currentButtonStates[8]) {
			if (currentButtonStates[8]) {
			} else {
			}
		} if (previousButtonStates[9] != currentButtonStates[9]) { // reverse direction
			if (currentButtonStates[9]) {
				direc *= -1;
			}
		} if (previousButtonStates[10] != currentButtonStates[10]) { // slow turn left
			if (currentButtonStates[10]){
				mecanumDrive.encoderTurn(10, 0.5);
			}
			else{
				mecanumDrive.brake();
			}

		} if (previousButtonStates[11] != currentButtonStates[11]) { // slow turn right
			if (currentButtonStates[11]){
				mecanumDrive.encoderTurn(-10, 0.5);
			}
			else{
				mecanumDrive.brake();
			}
		}
		previousButtonStates = currentButtonStates;
	}
	/*
	Controls:
	left bumper - intake
	right bumper - push servo + outtake + retract servo


	left joystck - movement
	right joystick - direction

	a, b, x, y: automations

	up, down, left, right: backups
	up - push servo
	down - retract servo

	*/

	/**
	Updates button list
	*/
	public boolean[] updateButtonList() {
		// Array with Button values order
		// Left Trigger, Right trigger, x, y, left_bumper, right_bumper, b, back
		boolean[] buttonList = {
			// main controls
			(gamepad2.left_trigger > 0), // insert command here (ex: start intake or move slide)
			(gamepad2.right_trigger > 0), // insert command here
			gamepad2.x, // insert command here
			gamepad2.y, // insert command here
			gamepad2.left_bumper, // insert command here
			gamepad2.right_bumper, // insert command here
			gamepad2.a, // insert command here
			gamepad2.b, // insert command here
			// gamepad1.back,
			gamepad2.dpad_down, // insert command here
			gamepad1.a,  // reverse robot
			gamepad1.dpad_left, // slow turn left (more accurate than sticks)
			gamepad1.dpad_right // slow turn right (more accurate than sticks)
		};
		return buttonList;
	}

	/*
	* Code to run ONCE after the driver hits STOP
	*/
	@Override
	public void stop() {
		mecanumDrive.brake();
		// stop all 
	}
}
