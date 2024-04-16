import axios from "axios";

interface Detail {
  detailNames: string[];
}

async function getAllDetails() {
  try {
    const { data } = await axios.get<Detail>(
      `http://localhost:8080/api/v1/manage/detailTypes/allDefs`
    );
    return data;
  } catch (error) {
    if (axios.isAxiosError(error)) {
      throw error;
    } else {
      throw error;
    }
  }
}

export { getAllDetails };
