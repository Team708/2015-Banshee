# FRC-2015
Software for the 2015 FRC robot, written by the Team 708 software team in Java.

##Subsystems
- Drivetrain
- Vision Processor
- Claw and Claw Elevator (same mechanical subsystem, treated as two separate ones in software)
- Indexer (also known as tote elevator)
- Intake

##Wiring
[See this Google Spreadsheet](https://docs.google.com/spreadsheets/d/1NOy_13O9_D_tmYfdHeO2wjOzWzNkkdXDBmnokGaqViw/edit?usp=sharing)

##Significant New Features
- Hatter Drive, which is just ChezyDrive with the namespace for anything interesting and original that we may add in the future.
- 4 Ball autonomous
- Now using percent error to determine motor speeds moving to a setpoint without PID control
- Simplified finding if a value is within a threshold by using |actual value - goal value| <= threshold value

##2015 Software Team (Quick Reference)
- @jpiergal (Mentor)
- @omn0mn0m (Head)
- @jlwang (Head)
- @brotibi
- @Ethreal
- @frakerman1
- @LabLayers
- @McFoaley
- @MichaelGipson101
- @UntamedMustache
- @wutnut
