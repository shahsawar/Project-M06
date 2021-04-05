echo "Enter project name: "
read artifactID
mvn archetype:generate \
        -DarchetypeGroupId=org.openjfx \
        -DarchetypeArtifactId=javafx-archetype-fxml \
        -DarchetypeVersion=0.0.5 \
        -DgroupId=com.github.gym \
        -DartifactId=$artifactID\
        -Dversion=version \
	-DinteractiveMode=false
