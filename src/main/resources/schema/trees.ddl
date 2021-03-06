CREATE VIEW trees AS
select t.*
from (
    select standortnr, max(timestamp) timestamp
    from tree_history
    group by standortnr
) maxTimestamp
inner join tree_history t
on         t.standortnr = maxTimestamp.standortnr
and        t.timestamp = maxTimestamp.timestamp;