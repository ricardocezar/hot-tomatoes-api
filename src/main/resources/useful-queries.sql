with winners as (
    select
        a.award_year,
        a.movie_id,
        m.title,
        mp.producer_id,
        p.name,
        ROW_NUMBER() OVER (PARTITION BY a.award_year, a.movie_id) rn
    from
        award a
        inner join movie m on a.movie_id = m.id
        inner join movie_producer mp on m.id = mp.movie_id
        inner join producer p on mp.producer_id = p.id
),
multi as (
    select
        winners.producer_id producer_id,
        winners.name name,
        count(1) qtd
    from
        winners
    group by
        producer_id,
        name
    having
        count(1) > 1
)


----------------------
select
    a.award_year,
    a.movie_id,
    m.title,
    mp.producer_id,
    p.name
from
    award a,
    multi
    inner join movie m on a.movie_id = m.id
    inner join movie_producer mp on m.id = mp.movie_id
    inner join producer p on mp.producer_id = p.id
where
    p.id = multi.producer_id

