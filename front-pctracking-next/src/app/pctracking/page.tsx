import { Pctracking } from "../models";

async function getPctrackingRequests(): Promise<Pctracking[]> {
    const response = await fetch(`http://localhost:8080/pctracking`);
    return response.json();
}


export default async function Pctracking() {
  const pctrackingRequests = await getPctrackingRequests();
  
  return (
    <main className="container mx-auto px-2">
        <article className="format format-invert">
            <h2 className={`mb-3 text-2xl font-semibold`}>
                Pctracking - requests
            </h2>
        </article>
        <table className="table-auto border-b font-medium dark:border-neutral-500">
            <tr>
                <th>id</th>
                <th>date time</th>
            </tr>
            {pctrackingRequests.map((pctracking) => (
                <tr className=" border-gray-700 bg-gray-600" key={pctracking.idPctracking}>
                    <td className="whitespace-nowrap font-medium text-white">
                        {pctracking.idPctracking}
                    </td>
                    <td className="whitespace-nowrap font-medium text-white">
                        {pctracking.dateTimeRequestTracking}
                    </td>
                </tr>
            ))}
        </table>
    </main>
  )
}
