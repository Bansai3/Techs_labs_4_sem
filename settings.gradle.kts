rootProject.name = "Bansai3"
include("lab-1", "lab-2", "lab-3", "lab-4", "lab-5")
include("lab-2:controller", "lab-2:dao", "lab-2:service")
include("lab-3:controllers", "lab-3:dao", "lab-3:service")
include("lab-4:controllers", "lab-4:dao", "lab-4:service")
include("lab-5:cat_microservice", "lab-5:owner_microservice",
        "lab-5:dto", "lab-5:jpa", "lab-5:endpoint_microservice")