import TitleLabel from "../components/TitleLabel/TitleLabel"
import ButtonCard from "../components/ButtonCard/ButtonCard"
function Home() {
    return (
    <div className="home-page">
      <div className="nameplate-container">
        <button className="signout-btn">Sign out</button>
        <h3 className="nameplate">Hi, username!</h3>
      </div>
      <TitleLabel title="Home" />
      <div className="button-container">
        <ButtonCard text="Create Entry" />
        <ButtonCard text="My Entries" />
      </div>
    </div>
  );
}

export default Home