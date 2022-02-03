# ShopQueues
_Simulation of a shop in which there are at most 5 cash registers. See over time the evolution of queues: how are they filling up or emptying._
## Functional Requirements
  - Create a simulation aiming to analyze queuing based systems for determining and minimizing clients’ waiting time
  - Generate random clients 
  - Use java threads. Multithreading: each thread is responsible for one queue.
  - Display the output: a “.txt” file is needed. The log of events will be displayed in that particular file, matching the pattern described in the presentation of the assignment

## Non-functional Requirements
  - Design or create a Graphical User Interface (GUI) in which the user can set the input data and see the real-time simulation
  - In the log of events file, append the number of clients that already left the shop, the average waiting period and the average processing/service period (taking into considerations only the clients who already left the shop), the peak hour and the number of clients present in queues at the peak hour

## Definitions
  - "No_of_clients" = how many clients are coming along the simulation
  - "No_of_queues" = how many queues we are using simultaneously
  - "Simulation_Time" = how many seconds we want the simulation to last
  - "Arrival_Time" = an interval specifying the possible time when client is arriving at our shop (a number is randomly chosen from this interval)
  - "Service_Time" = an interval specifying the duration of scanning the products of certain client (a number is randomly chosen from this interval)
  - So, a client will be written as (x, y, z), where x is client_ID, y is the Arrival_Time and z the Service_Time.

<p align="center">
    <i>General View of the Simulation</i>
</p>

<p align="center">
  <img width="900" src="https://user-images.githubusercontent.com/64836463/152319425-6c719cd2-9d30-40ff-bfd2-bf912bbbf6a0.png" alt="Init">
</p>

<p align="center">
    <i>Running</i>
</p>

<p align="center">
  <img width="900" src="https://user-images.githubusercontent.com/64836463/152319588-42993d39-b882-4053-9134-2585c5bc2e7f.png" alt="Running">
</p>
