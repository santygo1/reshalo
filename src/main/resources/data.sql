insert into detail_types(dtlt_id, type_def)
values (1, 'wheel');

insert into detail_type_characteristics(name, dtlt_dtlt_id)
values ('tirePressureInAtmospheres', 1),
       ('tireProfileInPercent', 1),
       ('diskSizeInInches', 1),
       ('treadDepthInMillimeters', 1),
       ('brakeThicknessInMillimeters', 1),
       ('diskOffsetInMillimeters', 1),
       ('wallHeightInMillimeters', 1),
       ('minimumTireToRimDistanceUnderLoad', 1);

-- группы предикатов, когда выполняются все предикаты из группы -> определяется поломка
-- если хотя бы один предикат не выполнился поломка определена не будет
insert into predicate_groups(dpgp_id, dtlt_dtlt_id, weight)
values (1, 1, 1),
       (2, 1, 20),
       (3, 1, 30),
       (4, 1, 40),
       (5, 1, 50);

-- предикаты
insert into predicates(dpgp_dpgp_id, condition_template)
values
    -- 1.	Утверждения, связывающие между собой состояние "спущена шина" и значения признаков:
    -- a.	если шина спущена, то значение признака давление в атмосферах ∈ [0; 2.1);
    -- b.	если шина спущена, то значение признака минимальное расстояние от шины до обода под нагрузкой ∈ [30; 55);
    -- c.	если шина спущена, то значение глубина протектора миллиметрах = 0.
    (1,
     '${failure_min_pressure} <= #{tirePressureInAtmospheres} && #{tirePressureInAtmospheres} < ${failure_max_pressure}'),
    (1, '#{treadDepthInMillimeters} == ${failure_treadDepthInMillimeters}'),
    (1, '${failure_min_minimumTireToRimDistanceUnderLoad} <= #{minimumTireToRimDistanceUnderLoad} &&' ||
        ' #{minimumTireToRimDistanceUnderLoad} < ${failure_min_minimumTireToRimDistanceUnderLoad}'),

-- 2.	Утверждения, связывающие между собой состояние "стерт протектор шины" и значения признаков:
-- a.	если стёрт протектор передней шины, то значение признака расстояние от шины до обода под нагрузкой ∈ [15; 55);
-- b.	если стёрт протектор передней шины, то значение глубина протектора миллиметрах = 0.
-- c.	стёрт протектор передней шины, то значение высота боковой стенки = 0.
    (2,
     '${failure_min_minimumTireToRimDistanceUnderLoad} <= #{minimumTireToRimDistanceUnderLoad} &&' ||
     ' {minimumTireToRimDistanceUnderLoad} < ${failure_min_minimumTireToRimDistanceUnderLoad}'),
    (2, '#{treadDepthInMillimeters} == ${failure_treadDepthInMillimeters}'),
    (2, '#{wallHeightInMillimeters} == 0'),
-- 3.	Утверждения, связывающие между собой состояние "поврежден диск" и значения признаков:
-- a.	если поврежден колесный диск, то значение признака размер диска ∈ (0; 15);
-- b.	если поврежден колесный диск, то значение признака вылет диска в миллиметрах ∈ (-20; -10) U (10; 20);
    (3,
     '${failure_min_diskSizeInInches} < #{diskSizeInInches} && #{diskSizeInInches} < ${failure_max_diskSizeInInches}'),
    (3,
     '(${failure_range1x_diskOffsetInMillimeters} < #{diskOffsetInMillimeters} && #{diskOffsetInMillimeters} < ${failure_range1y_diskOffsetInMillimeters}) ||' ||
     '(${failure_range2x_diskOffsetInMillimeters} < #{diskOffsetInMillimeters} && #{diskOffsetInMillimeters} < ${failure_range2y_diskOffsetInMillimeters})'),
-- 4.	Утверждения, связывающие между собой состояние "изношены тормозные диски" и значения признаков:
-- a.	Если  изношены тормозные диски, то значение признака толщина тормозного диска ∈ (0; 20).
    (4,
     '${failure_min_brakeThicknessInMillimeters} < #{brakeThicknessInMillimeters} && #{brakeThicknessInMillimeters} < ${failure_max_brakeThicknessInMillimeters}'),
-- 5.	Утверждения, связывающие между собой состояние "перекаченное колесо" и значения признаков:
-- a..	если шина спущена, то значение признака давление в атмосферах больше 2.4;
    (5,
     '#{tirePressureInAtmospheres} > ${failure_overinflated wheel_pressure}');


-- переменные
insert into variables("key", "value")
values
    -- переменные которые подставляются предикатов
    ('failure_min_pressure', 2.2),
    ('failure_max_pressure', 2.1),
    ('failure_overinflated wheel_pressure', 2.4),
    ('failure_min_minimumTireToRimDistanceUnderLoad', 30),
    ('failure_max_minimumTireToRimDistanceUnderLoad', 55),
    ('failure_treadDepthInMillimeters', 0),
    ('failure_wallHeightInMillimeters', 0),
    ('failure_min_diskSizeInInches', 0),
    ('failure_max_diskSizeInInches', 15),

    ('failure_range1x_diskOffsetInMillimeters', -20),
    ('failure_range1y_diskOffsetInMillimeters', -10),
    ('failure_range2x_diskOffsetInMillimeters', 10),
    ('failure_range2y_diskOffsetInMillimeters', 20),

    ('failure_min_brakeThicknessInMillimeters', 0),
    ('failure_max_brakeThicknessInMillimeters', 20),

    -- переменные которые подставляются в сообщения о ремонте
    ('message_normal_tirePressureInAtmospheres', 2.1)
;

-- Поломки которые определяются группой предикатов когда они выполняются
insert into breakages (dpgp_dpgp_id, def)
values (1, 'Спущена шина'),
       (2, 'Cтёрт протектор'),
       (3, 'Поврежден колесный диск'),
       (4, 'Изношен тормозной диск'),
       (5, 'Перекаченное колесо');

-- Действия для починки поломки
insert into repair_actions (brkg_brkg_id, def)
values
    -- 1.	Ремонт состояния "спущена шина":
    (1, 'Накачать шину до ${message_normal_tirePressureInAtmospheres}'),

    -- 2.	Ремонт состояния "стерт протектор":
    (2, 'разблокировать гайки колеса'),
    (2, 'поднять автомобиль с помощью домкрата'),
    (2, 'снять поврежденное колесо'),
    (2, 'установить запасное колесо на ступицу'),
    (2, 'затянуть гайки крест-накрест до полноценного контакта колеса со ступицей'),
    (2, 'опустить автомобиль домкратом'),
    (2, 'проверить давления в шине'),

    -- 3.	Ремонт состояния "поврежден колесный диск":
    (3, 'разблокировать гайки колеса'),
    (3, 'поднять автомобиль с помощью домкрата'),
    (3, 'снять поврежденное колесо'),
    (3, 'установить запасное колесо на ступицу'),
    (3, 'затянуть гайки крест-накрест до полноценного контакта колеса со ступицей'),
    (3, 'опустить автомобиль домкратом'),
    (3, 'проверить давления в шине'),

    -- 4.	Ремонт состояния "изношен тормозной диск";
    (4, 'поднятие автомобиль с помощью домкрата и установить на подставки для обеспечения безопасности'),
    (4, 'разблокировать гайки колеса'),
    (4, 'снять колесо для доступа к тормозной системе'),
    (4, 'открутить и снять суппорт тормозной системы'),
    (4, 'снять старые тормозные диски'),
    (4, 'установить и настроить новые тормозные диски'),
    (4, 'прикрутить на место суппорт тормозной системы'),
    (4, 'поставить колесо на место'),
    (4, 'прикрутить гайки колеса'),
    (4, 'опустить автомобиль с помощью домкрата'),

    -- 5.	Ремонт состояния "Перекаченное колесо";
    (5, 'Спустить колесо до ${message_normal_tirePressureInAtmospheres}')

