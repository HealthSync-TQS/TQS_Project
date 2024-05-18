import { Link } from "react-router-dom";

function NavBar({}) {
    return (
        <div className="navbar bg-base-100" style={{marginBottom: '80px'}}>
        <div className="flex-1">
          <div className="invisible sm:visible" >
            <Link to="/"className="btn hover:bg-white hover:border-white hover:-translate-y-0.5 " style={{color: '#2D3748'}}> Home</Link>
            <Link id="find" to="/find-reservation" className="btn hover:bg-white hover:border-white hover:-translate-y-0.5" style={{color: '#2D3748'}}> Find Reservation</Link>
          </div>
        </div>
        <div className="flex-none sm:invisible">
        <div className="dropdown dropdown-end">
            <label tabIndex={0} className="btn m-1 hover:bg-white hover:border-white"><svg viewBox="0 0 100 80" width="20" height="20"><rect width="100" height="20" rx="8"></rect><rect y="30" width="100" height="20" rx="8"></rect><rect y="60" width="100" height="20" rx="8"></rect></svg></label>
            <ul tabIndex={0} className="dropdown-content menu p-2 shadow bg-base-100 rounded-box w-52 drop-shadow-lg">
                <li><Link className="font-semibold" to="/Home">Home</Link></li>
                <li><Link className="font-semibold" to="/AllCollections">All Collections</Link></li>
                <li><Link className="font-semibold" to="/find-reservation">Find Reservation</Link></li>
            </ul>
            </div>
        </div>
      </div>
    );
}

export default NavBar;