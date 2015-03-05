records = LOAD 'tweets' USING PigStorage('#') AS (header, tweet);
hackathon_records = FILTER records BY (tweet matches '.*[hH]ackathon.*');
grouped_hack = GROUP hackathon_records ALL;
count_grouped_hack = FOREACH grouped_hack GENERATE 'hackathon', COUNT(hackathon_records);


dec_records = FILTER records BY (header matches '.*Dec.*');
grouped_dec = GROUP dec_records ALL;
count_grouped_dec = FOREACH grouped_dec GENERATE 'Dec', COUNT(dec_records);


chicago_records = FILTER records BY (tweet matches '.*Chicago.*');
grouped_chicago = GROUP chicago_records ALL;
count_grouped_chicago = FOREACH grouped_chicago GENERATE 'chicago', COUNT(chicago_records);


java_records = FILTER records BY (tweet matches '.*Java.*');
grouped_java = GROUP java_records ALL;
count_grouped_java = FOREACH grouped_java GENERATE 'Java', COUNT(java_records);


result = UNION count_grouped_hack, count_grouped_dec, count_grouped_chicago, count_grouped_java;
DUMP result;
