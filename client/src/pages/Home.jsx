import TitleLabel from "../components/TitleLabel/TitleLabel"
import ButtonCard from "../components/ButtonCard/ButtonCard"
function Home() {
    return (
    <div className="main-page">
      <div className="homePage-background"></div>
      <div className="nameplate-container">
        <ButtonCard text="Sign out" linkTo="/login" size="small"/>
        <h3 className="nameplate">Hi, username!</h3>
      </div>
      <TitleLabel title="Home" />
      <div className="button-container">
        <ButtonCard text="Create Entry" linkTo="/prompts" size="large" />
        <ButtonCard text="My Entries" linkTo="/entries" size="large"/>
      </div>
    </div>
  );
}

export default Home