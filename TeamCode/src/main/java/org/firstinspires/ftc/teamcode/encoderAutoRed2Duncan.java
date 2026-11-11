package org.firstinspires.ftc.teamcode;



import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
@Autonomous(name="red_Duncan2", group="Robot")

public class encoderAutoRed2Duncan extends LinearOpMode {
    private Servo sorter1;
    private Servo sorter2;
    private DcMotor fLD = null;
    private DcMotor fRD = null;
    private DcMotor bLD = null;
    private DcMotor bRD = null;
    private DcMotor pollen = null;
    private DcMotor nectar = null;
    private DcMotor intake = null;
    private ElapsedTime runtime = new ElapsedTime();
    static final double FORWARD_SPEED = 0.5;
    static final double TURN_SPEED = 0.4;
    public void runOpMode() {
        pollen = hardwareMap.get(DcMotor.class, "pollen");
        nectar = hardwareMap.get(DcMotor.class, "nectar");
        intake = hardwareMap.get(DcMotor.class, "intake");
        sorter1 = hardwareMap.get(Servo.class, "sorter1");
        sorter2 = hardwareMap.get(Servo.class, "sorter2");
        fLD = hardwareMap.get(DcMotor.class, "FLD");
        fRD = hardwareMap.get(DcMotor.class, "FRD");
        bLD = hardwareMap.get(DcMotor.class, "BLD");
        bRD = hardwareMap.get(DcMotor.class, "BRD");
        fLD.setDirection(DcMotor.Direction.FORWARD);
        fRD.setDirection(DcMotor.Direction.REVERSE);
        bLD.setDirection(DcMotor.Direction.FORWARD);
        bRD.setDirection(DcMotor.Direction.REVERSE);
        pollen.setDirection(DcMotor.Direction.FORWARD);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        pollen.setPower(1);
        sleep(500);
        set_all_motors_zero();
        strafeLeft(0.5, 1000, 10, 50);
        forward(0.5, 4000, 10);
        strafeLeft(0.5, 750, 10, 50);
set_all_motors_zero();
    }
    public void set_all_motors_zero() {
        fLD.setPower(0);
        fRD.setPower(0);
        bRD.setPower(0);
        bLD.setPower(0);
    }
    public void forward(double speed,
                        double ticks,
                        double timeoutS) {
        int newTarget;
        if (opModeIsActive()) {
            newTarget = fLD.getCurrentPosition() - (int) (ticks);
            fLD.setTargetPosition(newTarget);
            newTarget = fRD.getCurrentPosition() - (int) (ticks);
            fRD.setTargetPosition(newTarget);
            newTarget = bLD.getCurrentPosition() - (int) (ticks);
            bLD.setTargetPosition(newTarget);
            newTarget = bRD.getCurrentPosition() - (int) (ticks);
            bRD.setTargetPosition(newTarget);
            fLD.setMode(RUN_TO_POSITION);
            fRD.setMode(RUN_TO_POSITION);
            bLD.setMode(RUN_TO_POSITION);
            bRD.setMode(RUN_TO_POSITION);
            runtime.reset();
            fLD.setPower(Math.abs(speed));
            fRD.setPower(Math.abs(speed));
            bLD.setPower(Math.abs(speed));
            bRD.setPower(Math.abs(speed));
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (fLD.isBusy())) {
                telemetry.addData("Running to", " %7d", newTarget);
                telemetry.addData("Currently at ", fLD.getCurrentPosition());
                telemetry.update();
            }
            fLD.setPower(0);
            fRD.setPower(0);
            bLD.setPower(0);
            bRD.setPower(0);
            sleep(250);
        }
    }
    public void strafeLeft(double speed,
                           double ticks,
                           double timeoutS,
                           double strafe) {
        int newTarget;
        if (opModeIsActive()) {
            newTarget = fLD.getCurrentPosition() + (int) (ticks);
            fLD.setTargetPosition(newTarget);
            newTarget = fRD.getCurrentPosition() - (int) (ticks);
            fRD.setTargetPosition(newTarget);
            newTarget = bLD.getCurrentPosition() - (int) (ticks);
            bLD.setTargetPosition(newTarget);
            newTarget = bRD.getCurrentPosition() + (int) (ticks);
            bRD.setTargetPosition(newTarget);

            fLD.setMode(RUN_TO_POSITION);
            fRD.setMode(RUN_TO_POSITION);
            bLD.setMode(RUN_TO_POSITION);
            bRD.setMode(RUN_TO_POSITION);
            runtime.reset();

            fLD.setPower(Math.abs(speed));
            fRD.setPower(Math.abs(speed));
            bLD.setPower(Math.abs(speed));
            bRD.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (fLD.isBusy())) {
                telemetry.addData("Running to", " %7d", newTarget);
                telemetry.addData("Currently at ", fLD.getCurrentPosition());
                telemetry.update();
            }
            set_all_motors_zero();

            fLD.setPower(0);
            fRD.setPower(0);
            bLD.setPower(0);
            bRD.setPower(0);
            sleep(250);
        }
    }
    public void strafeRight(double speed,
                            double ticks,
                            double timeoutS,
                            double strafe) {
        int newTarget;
        if (opModeIsActive()) {
            newTarget = fLD.getCurrentPosition() - (int) (ticks);
            fLD.setTargetPosition(newTarget);
            newTarget = fRD.getCurrentPosition() + (int) (ticks);
            fRD.setTargetPosition(newTarget);
            newTarget = bLD.getCurrentPosition() + (int) (ticks);
            bLD.setTargetPosition(newTarget);
            newTarget = bRD.getCurrentPosition() - (int) (ticks);
            bRD.setTargetPosition(newTarget);

            fLD.setMode(RUN_TO_POSITION);
            fRD.setMode(RUN_TO_POSITION);
            bLD.setMode(RUN_TO_POSITION);
            bRD.setMode(RUN_TO_POSITION);
            runtime.reset();

            fLD.setPower(Math.abs(speed));
            fRD.setPower(Math.abs(speed));
            bLD.setPower(Math.abs(speed));
            bRD.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (fLD.isBusy())) {
                telemetry.addData("Running to", " %7d", newTarget);
                telemetry.addData("Currently at ", fLD.getCurrentPosition());
                telemetry.update();
            }
            set_all_motors_zero();

            fLD.setPower(0);
            fRD.setPower(0);
            bLD.setPower(0);
            bRD.setPower(0);
            sleep(250);
        }
    }
}