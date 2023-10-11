import { Pctracking } from "../models";

async function getPctrackingRequests(): Promise<Pctracking[]> {
    const response = await fetch(`http://ec2-52-1-121-229.compute-1.amazonaws.com/pctracking`);
    return await response.json();
}


export default async function Pctracking() {
  const pctrackingRequests = await getPctrackingRequests();
  
  return (
    <main className="container mx-auto px-2" suppressHydrationWarning>
        <article className="format format-invert">
            <h2 className={`mb-3 text-2xl font-semibold`}>
                Pctracking - requests
            </h2>
        </article>
        <div className="mb-32 grid text-center lg:max-w-5xl lg:w-full lg:mb-0 lg:grid-cols-4 lg:text-left">
        <table className="table-auto border-b font-medium dark:border-neutral-500">
            <tr className=" border-black bg-gray-400 border-2">
                <th>id</th>
                <th>date time</th>
            </tr>
            {pctrackingRequests.map((pctracking) => (
                <tr className=" border-black border-spacing-4 border-2 bg-gray-600" key={pctracking.idPctracking} suppressHydrationWarning>
                    <td className="whitespace-nowrap font-medium text-white">
                        {pctracking.idPctracking}
                    </td>
                    <td className="whitespace-nowrap font-medium text-white">
                        {pctracking.dateTimeRequestTracking}
                    </td>
                </tr>
            ))}
        </table>
        </div>
        <div className="fixed bottom-0 left-0 flex h-48 w-full items-end justify-center bg-gradient-to-t from-white via-white dark:from-black dark:via-black lg:static lg:h-auto lg:w-auto lg:bg-none">
          <a
            className=""
            href="https://github.com/alvesed/pctracking"
            target="_blank"
          >
            By alvesed - show me the code
          </a>
        </div>

    </main>
  )
}
