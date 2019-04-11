#1
	ALTER table developers ADD salary int default 300;
	UPDATE developers
	SET salary = Case id
			when 1 then 400
			when 2 then 450
            when 3 then 600
            else 300
            end
            where id >0;

	#2
	SELECT develop_proj.id_project, SUM(developers.salary) FROM develop_proj
	inner JOIN developers
	ON develop_proj.id_developers = developers.id
	where develop_proj.id_project =3;

	#3
	SELECT SUM(salary) FROM developers
	INNER JOIN developer_skills
	ON developer_skills.id_developers = developers.id
	WHERE (id_language=1);

	#4
	ALTER TABLE projects
	ADD COLUMN cost INT default 1000;

	#5
	SELECT develop_proj.id_project, projects.name, AVG(developers.salary) FROM develop_proj
	LEFT JOIN developers
	ON develop_proj.id_developers = developers.id
	INNER JOIN projects ON develop_proj.id_project=projects.id
	GROUP BY develop_proj.id_project
	ORDER BY SUM(developers.salary)
	limit 1;
