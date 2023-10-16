# Sequence Diagram

```plantuml
@startuml
actor User as user
participant " : UI" as ui 
ui -> user: Display search box 
ui -> user: Display filter and sort options
user -> ui: Enter name into search box
user -> ui: Select sort option
user -> ui: Select filter options

@enduml
```