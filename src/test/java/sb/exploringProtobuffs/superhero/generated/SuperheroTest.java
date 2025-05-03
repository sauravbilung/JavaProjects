package sb.exploringProtobuffs.superhero.generated;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class SuperheroTest {


        PersonalInformation.Builder personalInfoBuilder = PersonalInformation.newBuilder();
        PersonalInformation personalInfo = personalInfoBuilder
                .setRealName("Bruce Wayne")
                .setHeroName("Batman")
                .setGender(PersonalInformation.Gender.male)
                .build();

        SuperHeroAbilities.Builder superHeroAbilitiesBuilder = SuperHeroAbilities.newBuilder();
        SuperHeroAbilities superHeroAbilities = superHeroAbilitiesBuilder
                .setAbilitiesCommaSeparated("Money")
                .build();

    @Test
    void creatingPersonalInformation() {

        assertThat(personalInfo).isNotNull();
        assertThat(personalInfo.getHeroName()).isEqualTo("Batman");
        assertThat(personalInfo.getRealName()).isEqualTo("Bruce Wayne");
        assertThat(personalInfo.getGender()).isEqualTo(PersonalInformation.Gender.male);
    }

    @Test
    void SuperHero() {
        Superhero superhero = Superhero.newBuilder()
                .setPersonalInfo(personalInfo)
                .addPowers(superHeroAbilities)
                .build();

        assertThat(superhero.getPersonalInfo()).isNotNull();
        assertThat(superhero.getPowers(0)).isNotNull();
        System.out.println("SuperHero Personal Information : "+ superhero.getPersonalInfo());
        System.out.println("SuperHero Abilites :"+ superhero.getPowers(0));
    }
}