import { useQuery } from "@tanstack/react-query";
import React from "react";
import { getAllDetails } from "../api/details";

export const Form = () => {
  const { isPending, error, data } = useQuery({
    queryKey: ["todos"],
    queryFn: getAllDetails,
  });

  if (isPending) {
    return <h1>loading...</h1>;
  }

  if (error) {
    <h1>{error.message}</h1>;
  }

  return (
    <form className="mainForm">
      <label className="detailLabel" htmlFor="detail">
        Деталь:
      </label>
      <select id="detail">
        {data?.map((item) => (
          <option key={item} value={item}>
            {item}
          </option>
        ))}
      </select>
      <h2>Характеристики</h2>
      <div className="characteristic">
        <div className="characteristic__field">
          <label htmlFor="">DetailName</label>
          <input type="text" />
        </div>
        <div className="characteristic__field">
          <label htmlFor="">DetailName</label>
          <input type="text" />
        </div>
        <div className="characteristic__field">
          <label htmlFor="">DetailName</label>
          <input type="text" />
        </div>
      </div>
      <button type="submit">Определить поломку</button>
    </form>
  );
};
