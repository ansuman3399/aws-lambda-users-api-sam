AWS Lambda
----------

To run aws lambda function locally using SAM cli we need docker.
install sam cli as well..
after doing the changes  in the template.yaml and event.json , go to the directory where template.yml is present.
then build the lambda function in order to run in a docker container:
sam local invoke <ResourceName> --event events/event.json
this command will run the lambda function locally.

But in order to debug the lambda function locally:
Run the lambda function through sam cli and attach a debugger at the specified port by using the command:
sam local invoke <ResourceName> --event events/event.json -d 5858

now add the debug configuration in intellij,
click add configuration
select Debug remote JVM
enter the required port.
select the module.
save.


