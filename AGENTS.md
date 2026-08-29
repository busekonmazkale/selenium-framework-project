# Project Rules

## Selenium Grid

- Selenium Grid is started and managed manually outside this repository.
- Do not report the absence of automatic Selenium Grid startup in `Jenkinsfile` as a defect or high-risk finding.
- Assume the configured Grid is available to Jenkins unless the user explicitly asks for infrastructure or connectivity diagnostics.
- Do not add Docker, Grid startup, or Grid lifecycle automation unless the user explicitly requests it.
