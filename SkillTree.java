/**
 * the skill tree that will store skill nodes that enhance player ability
 */
public class SkillTree 
{
    private SkillNode root;


    public SkillTree(SkillNode root)
    {
        this.root = root;
    }

    public boolean hasNode(SkillNode node)
    {
        return root.contains(node);
    }

    public boolean hasSkill(Skill skill)
    {
        return root.containsSkill(skill);
    }

    public SkillNode getRoot()
    {
        return root;
    }

    public void setRoot(SkillNode root)
    {
        this.root = root;
    }
}
