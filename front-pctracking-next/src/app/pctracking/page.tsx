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
            {pctrackingRequests.map((pctracking) => (
                <div className=" border-black border-spacing-4 border-2 bg-gray-600" key={pctracking.idPctracking} suppressHydrationWarning>
                    <div className="whitespace-nowrap font-medium text-white">
                        {pctracking.idPctracking}
                    </div>
                    <div className="whitespace-nowrap font-medium text-white">
                        {pctracking.dateTimeRequestTracking}
                    </div>
                </div>
            ))}
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
