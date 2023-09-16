# library
It will track inventory in a library automation and relational database. The changes made in the relational database will be published to rabbitmq with an event-driven approach, and the remaining process will be taken over by the elasticdb repo so that the changes can be integrated into elasticsearch simultaneously.
