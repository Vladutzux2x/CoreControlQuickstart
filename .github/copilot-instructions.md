# Guidance for AI coding agents (concise)

- Repository type: Android Gradle multi-module project for the FTC Robot Controller.
- Two primary modules: `:FtcRobotController` (the Android app) and `:TeamCode` (team OpModes & libraries).

- Big picture:
  - `:FtcRobotController` contains the Robot Controller app and platform plumbing (UI, services, OpMode loader).
    See [FtcRobotController/src/main/java/org/firstinspires/ftc/robotcontroller/internal/FtcRobotControllerActivity.java](FtcRobotController/src/main/java/org/firstinspires/ftc/robotcontroller/internal/FtcRobotControllerActivity.java#L1)
  - `:TeamCode` is where teams add or modify OpModes and robot-specific code (Java/Kotlin). Example: [TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp.java](TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp.java#L1).
  - OpMode registration and discovery is driven by the controller's registration mechanism (see `FtcOpModeRegister.java` in the controller module).

- Build & deploy (Windows-specific examples):
  - Use the included Gradle wrapper from the repo root: `gradlew.bat`.
  - Build APKs: `gradlew.bat assembleDebug` (or target modules `:FtcRobotController:assembleDebug :TeamCode:assembleDebug`).
  - Prefer Android Studio (recommended in README) for iterative development, Logcat, and device deploy. The README requires Android Studio Ladybug (2024.2+) for best compatibility.
  - If installing manually after `assembleDebug`, use `adb install -r <apk>` or Android Studio's run/install tasks.
  - SDK/location: `local.properties` controls the Android SDK path on the developer machine.

- Project conventions and patterns to follow (only discoverable facts):
  - Team code belongs under `TeamCode/src/main/java/org/firstinspires/ftc/teamcode` and may mix Java and Kotlin (this module applies the Kotlin plugin).
  - Sample OpModes live under `FtcRobotController/src/main/java/org/firstinspires/ftc/robotcontroller/external/samples` — copy or adapt these when adding new examples.
  - Hardware/config files are managed by `RobotConfigFileManager` at runtime; do not assume a static path—use the existing APIs.
  - `TeamCode/build.gradle` applies `../build.common.gradle` and `../build.dependencies.gradle` — prefer adding minimal, local changes and avoid wholesale overrides.

- Key integration points & dependencies:
  - `TeamCode` has an explicit dependency on `project(':FtcRobotController')` and a remote Maven repo `https://remote.corecontrollib.com` (see `TeamCode/build.gradle`).
  - Mixed-language support: Kotlin and Java interoperability is in use (e.g., `TestDcMotor.kt` alongside Java OpModes).
  - Telemetry and logging use `RobotLog`/`Telemetry` patterns; runtime debugging is typically via Logcat and RobotLog output.

- Useful files to inspect when coding or troubleshooting:
  - [settings.gradle](settings.gradle#L1) — module layout (`:FtcRobotController`, `:TeamCode`).
  - [TeamCode/build.gradle](TeamCode/build.gradle#L1) — TeamCode compile targets and dependencies.
  - [FtcRobotControllerActivity.java](FtcRobotController/src/main/java/org/firstinspires/ftc/robotcontroller/internal/FtcRobotControllerActivity.java#L1) — app lifecycle, services, event loop.
  - Sample OpModes: `FtcRobotController/src/main/java/.../external/samples`.

- Non-goals / assumptions:
  - This file documents only concrete, discoverable patterns in the repo (not general best-practices).
  - Do not change Gradle plugin versions or Java target levels without confirming compatibility with the README and Android Studio version.

If any section is unclear or you want deeper examples (build matrix, exact install tasks, or OpMode registration details), tell me which area to expand and I will iterate.
