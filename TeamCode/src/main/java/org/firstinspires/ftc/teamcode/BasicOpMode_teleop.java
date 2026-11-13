/* Copyright (c) 2017 FIRST. All rights reserved.
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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/*
 * This file contains a minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When a selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

@TeleOp(name="Basic: Linear OpMode", group="Linear OpMode")
//@Disabled
public class BasicOpMode_teleop extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor bkLDrive = null;
    private DcMotor bkRDrive = null;
    private DcMotor ftLDrive = null;
    private DcMotor ftRDrive = null;
    private DcMotor intake = null;
    private CRServo intakeS1 = null;
    private CRServo intakeS2 = null;
    private CRServo sorter1 = null;
    private CRServo sorter2 = null;
    boolean toggle = false;
    boolean toggle2 = false;
   boolean toggle3 = false;


    private DcMotor shooter1 = null;
    private DcMotor shooter2 = null;
    //@Override

    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables.
        //
        //
        //Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        intake = hardwareMap.get(DcMotor.class,"intake");
        ftLDrive  = hardwareMap.get(DcMotor.class, "FLD");
        ftRDrive = hardwareMap.get(DcMotor.class, "FRD");
        bkRDrive = hardwareMap.get(DcMotor.class, "BRD");
        bkLDrive = hardwareMap.get(DcMotor.class, "BLD");
        shooter1 = hardwareMap.get(DcMotor.class,"shooter1");
       shooter2 = hardwareMap.get(DcMotor.class,"shooter2");
       intakeS1 = hardwareMap.get(CRServo.class,"InS1");
        intakeS2 = hardwareMap.get(CRServo.class,"InS2");
        sorter1 = hardwareMap.get(CRServo.class,"sort1");
        sorter2 = hardwareMap.get(CRServo.class,"sort2");


        bkLDrive.setDirection(DcMotor.Direction.FORWARD);
        bkRDrive.setDirection(DcMotor.Direction.REVERSE);
        ftLDrive.setDirection(DcMotor.Direction.FORWARD);
        ftRDrive.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.FORWARD);
        intakeS1.setDirection(DcMotor.Direction.REVERSE);
        intakeS2.setDirection(DcMotor.Direction.FORWARD);
        shooter1.setDirection(DcMotor.Direction.FORWARD);
        shooter2.setDirection(DcMotor.Direction.REVERSE);
        sorter1.setDirection(DcMotor.Direction.FORWARD);
        sorter2.setDirection(DcMotor.Direction.REVERSE);
        // Wait for the game to start (driver presses START)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {



            // Set up a variable for each drive wheel to save power level for telemetr
            double bkLeftPower;
            double bkRightPower;
            double ftLeftPower;
            double ftRightPower;
            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double drive = -gamepad1.left_stick_y;
            double strafe =  gamepad1.left_stick_x ;
            double rotate =  gamepad1.right_stick_x;

            ftLeftPower = Range.clip(drive - strafe - rotate, -0.9, 0.9);
            ftRightPower = Range.clip(drive + strafe + rotate, -0.9, 0.9);
            bkLeftPower = Range.clip(drive + strafe - rotate, -0.9, 0.9);
            bkRightPower = Range.clip(drive - strafe + rotate, -0.9, 0.9);

            // Tank Mode uses one stick to control each wheel.
            // - requires no math,but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y;
            // rightPower = -gamepad1.right_stick_y;

            // Send calculated power to wheels
            bkLDrive.setPower(bkLeftPower);
            bkRDrive.setPower(bkRightPower);
            ftLDrive.setPower(ftLeftPower);
            ftRDrive.setPower(ftRightPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "front left (%.2f)," +
                    " front right (%.2f), back left (%.2f), back right (%.2f)",
                    ftLeftPower, ftLeftPower,bkLeftPower,bkRightPower);
            telemetry.update();
        }
 if (gamepad1.leftTriggerWasPressed()){
     toggle2 = !toggle2;
 }
      if (toggle2){
          shooter1.setPower(0.5);
      }
        else{
            shooter1.setPower(0);
      }

         if(gamepad1.aWasPressed()){
             toggle = !toggle;
         }
        if (toggle){
            intake.setPower(0.5);
        }
        else{
            intake.setPower(0);
        }

            if (gamepad1.rightTriggerWasPressed()){
                toggle3 = !toggle3;
            }

            if (toggle3){
                shooter2.setPower(0.5);
            }

             else{
             shooter2.setPower(0);
            }









                }
}
