# validity-exp

## Prerequisites
To use this exp work, you need to have:

- Java >= 1.8 (1.8, 11 to 17 are verified. Please make sure the environment path has been set accordingly).
- Maven >= 3.6 (If you want to compile and install IoTDB from source code).
- IoTDB and IoTDB-UDF from https://github.com/apache/iotdb/tree/research/quality-validity

## open_source_data

- This contains all public datasets used in the experiments, including UCI-Energy and UCI-PAMAP2.
  - energy: containts UCI-Energy dataset;
  - PAMAP2:  containts UCI-PAMAP2 dataset;
  - muss_data: data set for overlap rate experimental part;
  - pre_compute: data set for the pre-computation test;;

## exp_code

- This part is the experiment related code.
  - exp1 calculates the difference between violation and repair, and you need to register violationUDF first.
  - exp2 and exp3 are time statistical experiments, we use the statistical information that comes with iotdb, i.e, the results displayed on the client.
  - exp4 is divided into two parts, which calculate the full result and the period result respectively.

## Some details

- For exp2, there needs update settings in 'iotdb-engine.properties' of IoTDB, includes 'max_number_of_points_in_page', 'enable_unseq_space_compaction', 'enable_cross_space_compaction' and 'enable_timed_flush_unseq_memtable'.
- If you want to use defined speed constraints, you should update 'usePreSpeed' as true and 'sMax', 'sMin' as defined constraints in 'iotdb-engine.properties'.
